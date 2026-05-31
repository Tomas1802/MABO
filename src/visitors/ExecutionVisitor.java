package visitors;

import filesystem.FileSystemService;
import security.SecurityValidator;
import gen.proyectoBaseVisitor;
import gen.proyectoLexer;
import gen.proyectoParser;
import logs.LoggerService;
import logs.TaskLogService;
import model.Task;
import scheduler.SchedulerService;
import scheduler.ScheduleStore;
import scheduler.StartupRegistrationService;
import semantic.ExecutionContext;
import shell.ShellCommandService;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import utils.FilterUtils;

import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExecutionVisitor extends proyectoBaseVisitor<Object> {
    private final ExecutionContext ctx;
    private final FileSystemService fs;
    private final LoggerService logger;
    private final TaskLogService taskLogger;
    private final ShellCommandService shell;
    private final StartupRegistrationService startupRegistration;
    private final SchedulerService scheduler = new SchedulerService();
    private final ScheduleStore scheduleStore = new ScheduleStore();
    private final java.util.Set<String> scheduledTasks = new java.util.HashSet<>();
    private final Map<String, ScheduledFuture<?>> scheduledFutures = new HashMap<>();
    private final java.util.Set<String> importedFiles = new java.util.HashSet<>();
    private final ThreadLocal<String> runningTask = new ThreadLocal<>();
    private final Path scriptPath;

    public boolean hayTareasProgramadas() {
        return !scheduledTasks.isEmpty();
    }

    public Path getCurrentDirectory() {
        return fs.getWorkingDirectory();
    }

    public Path getTaskLogDirectory() {
        return taskLogger.getLogDirectory();
    }

    public ExecutionVisitor(ExecutionContext ctx, LoggerService logger) {
        this(ctx, new FileSystemService(), logger, null);
    }

    public ExecutionVisitor(ExecutionContext ctx, FileSystemService fs, LoggerService logger) {
        this(ctx, fs, logger, null);
    }

    public ExecutionVisitor(ExecutionContext ctx, LoggerService logger, Path scriptPath) {
        this(ctx, new FileSystemService(), logger, scriptPath);
    }

    public ExecutionVisitor(ExecutionContext ctx, FileSystemService fs, LoggerService logger, Path scriptPath) {
        this.ctx = ctx;
        this.fs = fs == null ? new FileSystemService() : fs;
        this.logger = logger;
        this.taskLogger = new TaskLogService();
        this.shell = new ShellCommandService(logger);
        this.startupRegistration = new StartupRegistrationService(logger);
        this.scriptPath = scriptPath == null ? null : scriptPath.toAbsolutePath().normalize();
    }

    @Override
    public Object visitPrograma(proyectoParser.ProgramaContext node) {
        return visitChildren(node);
    }

    @Override
    public Object visitImportar(proyectoParser.ImportarContext node) {
        String rawPath = stripQuotes(node.cadena().getText());
        Path importPath = fs.pathOf(rawPath);
        String normalized = importPath.toAbsolutePath().normalize().toString();
        if (importedFiles.contains(normalized)) {
            logger.info("Import ignorado (ya cargado): " + normalized);
            return null;
        }
        importedFiles.add(normalized);
        try {
            proyectoLexer lexer = new proyectoLexer(CharStreams.fromPath(importPath));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            proyectoParser parser = new proyectoParser(tokens);
            proyectoParser.ProgramaContext importedProgram = parser.programa();
            logger.info("Importando script: " + normalized);
            visit(importedProgram);
            return null;
        } catch (Exception ex) {
            throw new RuntimeException("Error importando script '" + rawPath + "': " + ex.getMessage(), ex);
        }
    }

    @Override
    public Object visitDeclaracionVariable(proyectoParser.DeclaracionVariableContext node) {
        Object value = visit(node.expresion());
        String name = node.ID().getText();
        // Expand env vars; if it resolves to a valid path, store the normalized path string
        if (value instanceof String s) {
            String expanded = fs.expandTokens(s);
            try {
                java.nio.file.Path p = fs.pathOf(expanded);
                SecurityValidator.addAllowedRoot(p);
                value = p.toString();
                logger.info("Ruta añadida a raíces permitidas: " + p);
            } catch (RuntimeException ex) {
                value = expanded;
                logger.info("No se pudo registrar ruta permitida para variable " + name + ": " + ex.getMessage());
            }
        }
        this.ctx.variables.put(name, value);
        setLast(value);
        logger.info("Variable guardada: " + name + " = " + value);
        return value;
    }

    @Override
    public Object visitAsignacionVariable(proyectoParser.AsignacionVariableContext node) {
        Object value = visit(node.expresion());
        String name = node.ID().getText();
        this.ctx.variables.put(name, value);
        setLast(value);
        logger.info("Variable asignada: " + name + " = " + value);
        return value;
    }

    @Override
    public Object visitMostrar(proyectoParser.MostrarContext node) {
        Object value = visit(node.expresion());
        setLast(value);
        String display = formatValue(value);
        System.out.println(display);
        logger.info("Mostrar: " + display);
        taskEvent("Mostrar: " + display);
        return value;
    }

    @Override
    public Object visitCambiarDirectorio(proyectoParser.CambiarDirectorioContext node) {
        Path path = fs.changeWorkingDirectory(visit(node.expresion()));
        setLast(path);
        System.out.println(path);
        logger.info("Directorio actual: " + path);
        return path;
    }

    @Override
    public Object visitMostrarRuta(proyectoParser.MostrarRutaContext node) {
        Path path = fs.getWorkingDirectory();
        setLast(path);
        System.out.println(path);
        logger.info("MostrarRuta: " + path);
        return path;
    }

    @Override
    public Object visitTarea(proyectoParser.TareaContext node) {
        String name = node.ID().getText();
        this.ctx.tareas.put(name, new Task(name, node.bloque()));
        logger.info("Tarea registrada: " + name);
        return null;
    }

    @Override
    public Object visitEjecutarTarea(proyectoParser.EjecutarTareaContext node) {
        String name = node.ID().getText();
        if (node.programacion() == null) {
            executeTask(name);
            return null;
        }

        scheduleTask(name, node.programacion());
        return null;
    }

    @Override
    public Object visitListarTareasProgramadas(proyectoParser.ListarTareasProgramadasContext node) {
        List<ScheduleStore.ScheduleEntry> schedules = scheduleStore.loadSchedules();
        setLast(schedules);
        if (schedules.isEmpty()) {
            System.out.println("No hay tareas programadas.");
            return schedules;
        }
        for (ScheduleStore.ScheduleEntry schedule : schedules) {
            String line = formatSchedule(schedule);
            System.out.println(line);
        }
        return schedules;
    }

    @Override
    public Object visitEliminarTareaProgramada(proyectoParser.EliminarTareaProgramadaContext node) {
        String name = node.ID().getText();
        boolean removed = cancelScheduledTask(name, true);
        System.out.println(removed ? "Tarea programada eliminada: " + name : "No existe tarea programada: " + name);
        return removed;
    }

    @Override
    public Object visitEliminarTareasProgramadas(proyectoParser.EliminarTareasProgramadasContext node) {
        int removed = cancelAllScheduledTasks();
        System.out.println(removed > 0
                ? "Tareas programadas eliminadas: " + removed
                : "No hay tareas programadas.");
        return removed;
    }

    @Override
    public Object visitCambiarTareaProgramada(proyectoParser.CambiarTareaProgramadaContext node) {
        String name = node.ID().getText();
        scheduleTask(name, node.programacion());
        System.out.println("Programación actualizada: " + name);
        return null;
    }

    private void scheduleTask(String taskName, proyectoParser.ProgramacionContext node) {
        if (!ctx.tareas.containsKey(taskName)) {
            logger.error("No se pudo programar tarea no definida: " + taskName);
            taskLogger.scheduler("No se pudo programar tarea no definida: " + taskName);
            return;
        }
        cancelScheduledTask(taskName, true);
        if (node.CADA() != null) {
            long amount = parseNumero(node.numero());
            TimeUnit unit = toTimeUnit(node.tiempo().getText());
            if (amount <= 0) {
                logger.info("Programación ignorada para " + taskName + ": periodo inválido " + amount);
                return;
            }
            ScheduledFuture<?> future = scheduler.scheduleAtFixedRateWithHandle(() -> runScheduledTask(taskName), 0, amount, unit);
            scheduledFutures.put(taskName, future);
            scheduledTasks.add(taskName);
            long seconds = unit.toSeconds(amount);
            scheduleStore.saveIntervalSchedule(taskName, seconds);
            logger.info("Programada tarea " + taskName + " cada " + amount + " " + unit);
            taskLogger.scheduler("Programada tarea " + taskName + " cada " + amount + " " + node.tiempo().getText());
            System.out.println("Tarea programada: " + taskName + " cada " + amount + " " + node.tiempo().getText());
            executeTask(taskName);
        } else if (node.LAS() != null) {
            LocalTime time = parseTime(stripQuotes(node.cadena().getText()));
            LocalDateTime nextRun = nextRunAt(time);
            long initialDelay = secondsUntil(nextRun);
            ScheduledFuture<?> future = scheduler.scheduleOnce(() -> runOneTimeScheduledTask(taskName), initialDelay, TimeUnit.SECONDS);
            scheduledFutures.put(taskName, future);
            scheduledTasks.add(taskName);
            scheduleStore.saveOneTimeSchedule(taskName, nextRun);
            logger.info("Programada tarea " + taskName + " a las " + time + "; próxima ejecución=" + nextRun + "; espera=" + initialDelay + "s");
            taskLogger.scheduler("Programada tarea " + taskName + " a las " + time + "; próxima ejecución=" + nextRun + "; espera=" + initialDelay + "s");
            System.out.println("Tarea programada: " + taskName + " a las " + time + " (proxima ejecucion: " + nextRun + ")");
        } else {
            boolean registered = startupRegistration.register(taskName, scriptPath);
            scheduleStore.saveStartupSchedule(taskName);
            scheduledTasks.add(taskName);
            logger.info("Ejecución al iniciar sistema de tarea: " + taskName + " registrada=" + registered);
            taskLogger.scheduler("Programada tarea al iniciar sistema: " + taskName + " registrada=" + registered);
            System.out.println("Tarea programada al iniciar sistema: " + taskName);
            executeTask(taskName);
        }
    }

    public void restoreSchedules() {
        java.util.List<ScheduleStore.ScheduleEntry> list = scheduleStore.loadSchedules();
        for (ScheduleStore.ScheduleEntry se : list) {
            if (scheduledTasks.contains(se.task)) continue; // already scheduled in this run
            if (!ctx.tareas.containsKey(se.task)) {
                logger.info("No existe tarea para reprogramar: " + se.task);
                continue;
            }
            if ("once".equalsIgnoreCase(se.kind)) {
                LocalDateTime fireAt = parseFireAt(se.fireAt);
                if (!fireAt.isAfter(LocalDateTime.now())) {
                    scheduleStore.deleteSchedule(se.task);
                    taskLogger.scheduler("Tarea de una sola ejecución expirada y eliminada: " + se.task + " fireAt=" + se.fireAt);
                    continue;
                }
                ScheduledFuture<?> future = scheduler.scheduleOnce(() -> runOneTimeScheduledTask(se.task), secondsUntil(fireAt), TimeUnit.SECONDS);
                scheduledFutures.put(se.task, future);
                scheduledTasks.add(se.task);
                logger.info("Restaurada tarea de una sola ejecución: " + se.task + " en " + fireAt);
                taskLogger.scheduler("Restaurada tarea de una sola ejecución: " + se.task + " en " + fireAt);
                continue;
            }
            if ("startup".equalsIgnoreCase(se.kind)) {
                startupRegistration.register(se.task, scriptPath);
                scheduledTasks.add(se.task);
                logger.info("Restaurada tarea de inicio del sistema: " + se.task);
                taskLogger.scheduler("Restaurada tarea de inicio del sistema: " + se.task);
                continue;
            }
            if (se.periodSeconds <= 0) {
                logger.info("Entrada de schedule inválida ignorada para " + se.task + ": " + se.periodSeconds + "s");
                continue;
            }
            ScheduledFuture<?> future = scheduler.scheduleAtFixedRateWithHandle(() -> runScheduledTask(se.task), 0, se.periodSeconds, java.util.concurrent.TimeUnit.SECONDS);
            scheduledFutures.put(se.task, future);
            scheduledTasks.add(se.task);
            logger.info("Restaurada tarea programada: " + se.task + " cada " + se.periodSeconds + "s");
            taskLogger.scheduler("Restaurada tarea programada: " + se.task + " cada " + se.periodSeconds + "s");
        }
    }

    private LocalTime parseTime(String text) {
        try {
            return LocalTime.parse(text);
        } catch (Exception ex) {
            throw new exceptions.ExecutionException("Hora inválida para programación. Usa HH:mm, recibido: " + text);
        }
    }

    private long secondsUntil(LocalTime time) {
        return secondsUntil(nextRunAt(time));
    }

    private long secondsUntil(LocalDateTime next) {
        return Math.max(1L, Duration.between(LocalDateTime.now(), next).toSeconds());
    }

    private LocalDateTime nextRunAt(LocalTime time) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.withHour(time.getHour()).withMinute(time.getMinute()).withSecond(0).withNano(0);
        if (!next.isAfter(now)) {
            next = next.plusDays(1);
        }
        return next;
    }

    private LocalDateTime parseFireAt(String text) {
        if (text == null || text.isBlank()) {
            return LocalDateTime.now().minusSeconds(1);
        }
        try {
            return LocalDateTime.parse(text);
        } catch (Exception ignored) {
            return nextRunAt(parseTime(text));
        }
    }

    private long parseNumero(proyectoParser.NumeroContext numero) {
        if (numero == null) return 0L;
        String text = numero.getText();
        try {
            if (text.contains(".")) {
                return (long) Double.parseDouble(text);
            }
            return Long.parseLong(text);
        } catch (NumberFormatException ex) {
            logger.info("No se pudo parsear número en programación: " + text);
            return 0L;
        }
    }

    @Override
    public Object visitCondicion(proyectoParser.CondicionContext node) {
        Object raw = visit(node.expresion());
        boolean condition = toBoolean(raw);
        String exprText = node.expresion().getText();
        if (raw == null) {
            if ("VERDADERO".equalsIgnoreCase(exprText) || "TRUE".equalsIgnoreCase(exprText) || "1".equals(exprText)) {
                condition = true;
            } else if ("FALSO".equalsIgnoreCase(exprText) || "FALSE".equalsIgnoreCase(exprText) || "0".equals(exprText)) {
                condition = false;
            } else {
                // try variable lookup
                Object var = this.ctx.variables.get(exprText);
                if (var != null) {
                    condition = toBoolean(var);
                    raw = var;
                }
            }
        }
        logger.info("Condicion: expresion='" + exprText + "' -> valorEvaluado=" + condition + " (raw=" + raw + ")");
        if (condition) {
            visit(node.bloque(0));
        } else if (node.bloque().size() > 1) {
            visit(node.bloque(1));
        }
        return condition;
    }

    @Override
    public Object visitCicloMientras(proyectoParser.CicloMientrasContext node) {
        int iterations = 0;
        while (toBoolean(visit(node.expresion())) && iterations < 1000) {
            visit(node.bloque());
            iterations++;
        }
        return null;
    }

    @Override
    public Object visitCicloParaCada(proyectoParser.CicloParaCadaContext node) {
        Object collection = visit(node.expresion());
        if (collection instanceof Iterable<?> iterable) {
            String name = node.ID().getText();
            for (Object item : iterable) {
                this.ctx.variables.put(name, item);
                visit(node.bloque());
            }
        }
        return null;
    }

    @Override
    public Object visitIntentarCapturar(proyectoParser.IntentarCapturarContext node) {
        try {
            visit(node.bloque(0));
        } catch (exceptions.ReturnException ret) {
            throw ret;
        } catch (Exception ex) {
            logger.info("Error capturado en Intentar: " + ex.getMessage());
            visit(node.bloque(1));
        }
        return null;
    }

    @Override
    public Object visitSimulacion(proyectoParser.SimulacionContext node) {
        boolean previous = this.ctx.modoSimulacion;
        this.ctx.modoSimulacion = true;
        try {
            if (node.accionArchivo() != null) {
                visit(node.accionArchivo());
            } else if (node.comandoSistema() != null) {
                visit(node.comandoSistema());
            }
        } finally {
            this.ctx.modoSimulacion = previous;
        }
        return null;
    }

    @Override
    public Object visitEjecutarPowerShell(proyectoParser.EjecutarPowerShellContext node) {
        Object result = shell.execute("powershell", stringify(visit(node.expresion())), this.ctx.modoSimulacion);
        setLast(result);
        System.out.println(result);
        taskEvent("PowerShell: " + result);
        return result;
    }

    @Override
    public Object visitEjecutarLinux(proyectoParser.EjecutarLinuxContext node) {
        Object result = shell.execute("linux", stringify(visit(node.expresion())), this.ctx.modoSimulacion);
        setLast(result);
        System.out.println(result);
        taskEvent("Linux: " + result);
        return result;
    }

    @Override
    public Object visitCrearArchivo(proyectoParser.CrearArchivoContext node) {
        Path path = toPath(visit(node.expresion()));
        taskEvent((ctx.modoSimulacion ? "Simular crear archivo: " : "Crear archivo: ") + path);
        fs.createFile(path, this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitCrearCarpeta(proyectoParser.CrearCarpetaContext node) {
        Path path = toPath(visit(node.expresion()));
        taskEvent((ctx.modoSimulacion ? "Simular crear carpeta: " : "Crear carpeta: ") + path);
        fs.createDirectories(path, this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitLeerArchivo(proyectoParser.LeerArchivoContext node) {
        String content = fs.readText(toPath(visit(node.expresion())));
        setLast(content);
        System.out.println(content);
        logger.info("LeerArchivo ejecutado");
        taskEvent("Leer archivo: " + node.expresion().getText() + " (" + content.length() + " caracteres)");
        return content;
    }

    @Override
    public Object visitEscribirArchivo(proyectoParser.EscribirArchivoContext node) {
        String content = stringify(visit(node.expresion(0)));
        Path path = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular escribir: " : "Escribir: ") + path + " (" + content.length() + " caracteres)");
        fs.writeText(path, content, false, this.ctx.modoSimulacion);
        setLast(content);
        return content;
    }

    @Override
    public Object visitAnexarArchivo(proyectoParser.AnexarArchivoContext node) {
        String content = stringify(visit(node.expresion(0)));
        Path path = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular anexar: " : "Anexar: ") + path + " (" + content.length() + " caracteres)");
        fs.writeText(path, content, true, this.ctx.modoSimulacion);
        setLast(content);
        return content;
    }

    @Override
    public Object visitEliminarArchivo(proyectoParser.EliminarArchivoContext node) {
        Path path = toPath(visit(node.expresion()));
        taskEvent((ctx.modoSimulacion ? "Simular eliminar archivo: " : "Eliminar archivo: ") + path
                + (node.sinConfirmar() != null ? " (sin confirmar)" : " (con confirmación)"));
        fs.delete(path, this.ctx.modoSimulacion, node.sinConfirmar() != null);
        return null;
    }

    @Override
    public Object visitEliminarCarpeta(proyectoParser.EliminarCarpetaContext node) {
        Path path = toPath(visit(node.expresion()));
        taskEvent((ctx.modoSimulacion ? "Simular eliminar carpeta: " : "Eliminar carpeta: ") + path
                + (node.sinConfirmar() != null ? " (sin confirmar)" : " (con confirmación)"));
        fs.delete(path, this.ctx.modoSimulacion, node.sinConfirmar() != null);
        return null;
    }

    @Override
    public Object visitCopiarArchivo(proyectoParser.CopiarArchivoContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular copiar archivo: " : "Copiar archivo: ") + source + " -> " + target);
        fs.copy(source, target, this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitCopiarCarpeta(proyectoParser.CopiarCarpetaContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular copiar carpeta: " : "Copiar carpeta: ") + source + " -> " + target);
        fs.copy(source, target, this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitMoverArchivo(proyectoParser.MoverArchivoContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular mover archivo: " : "Mover archivo: ") + source + " -> " + target);
        fs.move(source, target, this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitMoverCarpeta(proyectoParser.MoverCarpetaContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular mover carpeta: " : "Mover carpeta: ") + source + " -> " + target);
        fs.move(source, target, this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitRenombrarArchivo(proyectoParser.RenombrarArchivoContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular renombrar archivo: " : "Renombrar archivo: ") + source + " -> " + target);
        fs.move(source, target, this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitRenombrarCarpeta(proyectoParser.RenombrarCarpetaContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular renombrar carpeta: " : "Renombrar carpeta: ") + source + " -> " + target);
        fs.move(source, target, this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitBuscarArchivos(proyectoParser.BuscarArchivosContext node) {
        Path root = toPath(visit(node.expresion()));
        List<Path> results = fs.findFiles(root, buildFilter(node.filtros()));
        this.ctx.archivosEncontrados.clear();
        this.ctx.archivosEncontrados.addAll(results);
        setLast(results);
        results.forEach(System.out::println);
        taskEvent("Buscar archivos en " + root + ": " + results.size() + " resultado(s)");
        results.forEach(path -> taskEvent("  " + path));
        return results;
    }

    @Override
    public Object visitBuscarCarpetas(proyectoParser.BuscarCarpetasContext node) {
        Path root = toPath(visit(node.expresion()));
        List<Path> results = fs.findDirectories(root, buildFilter(node.filtros()));
        this.ctx.archivosEncontrados.clear();
        this.ctx.archivosEncontrados.addAll(results);
        setLast(results);
        results.forEach(System.out::println);
        taskEvent("Buscar carpetas en " + root + ": " + results.size() + " resultado(s)");
        results.forEach(path -> taskEvent("  " + path));
        return results;
    }

    @Override
    public Object visitListarContenido(proyectoParser.ListarContenidoContext node) {
        Path target = node.expresion() == null ? fs.getWorkingDirectory() : toPath(visit(node.expresion()));
        List<Path> children = fs.listChildren(target);
        setLast(children);
        children.forEach(System.out::println);
        taskEvent("Listar contenido en " + target + ": " + children.size() + " elemento(s)");
        children.forEach(path -> taskEvent("  " + path));
        return children;
    }

    @Override
    public Object visitExisteArchivo(proyectoParser.ExisteArchivoContext node) {
        boolean exists = java.nio.file.Files.isRegularFile(toPath(visit(node.expresion())));
        setLast(exists);
        return exists;
    }

    @Override
    public Object visitExisteCarpeta(proyectoParser.ExisteCarpetaContext node) {
        boolean exists = java.nio.file.Files.isDirectory(toPath(visit(node.expresion())));
        setLast(exists);
        return exists;
    }

    @Override
    public Object visitObtenerTamano(proyectoParser.ObtenerTamanoContext node) {
        Object target = visit(node.expresion());
        long size = fs.sizeOf(toPath(target));
        setLast(size);
        return size;
    }

    @Override
    public Object visitObtenerCantidadArchivos(proyectoParser.ObtenerCantidadArchivosContext node) {
        Object val = visit(node.expresion());
        long count;
        if (val instanceof java.util.List<?> list) {
            count = list.size();
        } else {
            count = fs.countFiles(toPath(val));
        }
        setLast(count);
        return count;
    }

    @Override
    public Object visitObtenerPropiedad(proyectoParser.ObtenerPropiedadContext node) {
        Object value = fs.readProperty(toPath(visit(node.expresion())), node.ID().getText());
        setLast(value);
        return value;
    }

    @Override
    public Object visitComprimir(proyectoParser.ComprimirContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular comprimir: " : "Comprimir: ") + source + " -> " + target);
        fs.compress(source, target, ctx.modoSimulacion);
        if (!ctx.modoSimulacion) System.out.println("Comprimido: " + source + " -> " + target);
        return null;
    }

    @Override
    public Object visitDescomprimir(proyectoParser.DescomprimirContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular descomprimir: " : "Descomprimir: ") + source + " -> " + target);
        fs.decompress(source, target, ctx.modoSimulacion);
        if (!ctx.modoSimulacion) System.out.println("Descomprimido: " + source + " -> " + target);
        return null;
    }

    @Override
    public Object visitCambiarPermisos(proyectoParser.CambiarPermisosContext node) {
        Path path = toPath(visit(node.expresion(0)));
        String permissions = stringify(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular cambiar permisos: " : "Cambiar permisos: ") + path + " -> " + permissions);
        fs.changePermissions(path, permissions, ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitCrearBackup(proyectoParser.CrearBackupContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        taskEvent((ctx.modoSimulacion ? "Simular backup: " : "Crear backup: ") + source + " -> " + target);
        fs.copy(source, target, this.ctx.modoSimulacion);
        return null;
    }

    // -------------------------------------------------------------------------
    // FUNCIONES
    // -------------------------------------------------------------------------

    @Override
    public Object visitFuncion(proyectoParser.FuncionContext node) {
        String name = node.ID().getText();
        ctx.funciones.put(name, node);
        logger.info("Función registrada: " + name);
        return null;
    }

    @Override
    public Object visitRetornar(proyectoParser.RetornarContext node) {
        Object value = visit(node.expresion());
        throw new exceptions.ReturnException(value);
    }

    @Override
    public Object visitLlamadaFuncion(proyectoParser.LlamadaFuncionContext node) {
        String name = node.ID().getText();
        proyectoParser.FuncionContext funDef = ctx.funciones.get(name);
        if (funDef == null) {
            throw new exceptions.SemanticException("Función no definida: '" + name + "'");
        }

        // Recolectar nombres de parámetros
        List<String> params = new ArrayList<>();
        if (funDef.parametros() != null) {
            for (var token : funDef.parametros().ID()) {
                params.add(token.getText());
            }
        }

        // Evaluar argumentos
        List<Object> args = new ArrayList<>();
        if (node.argumentos() != null) {
            for (var expr : node.argumentos().expresion()) {
                args.add(visit(expr));
            }
        }

        if (params.size() != args.size()) {
            throw new exceptions.SemanticException(
                "Función '" + name + "' espera " + params.size() + " argumento(s), recibió " + args.size());
        }

        // Conservar scope actual y crear scope de la función
        Map<String, Object> savedScope = new HashMap<>(ctx.variables);
        for (int i = 0; i < params.size(); i++) {
            ctx.variables.put(params.get(i), args.get(i));
        }

        Object returnValue = null;
        try {
            visit(funDef.bloque());
        } catch (exceptions.ReturnException ret) {
            returnValue = ret.value;
        } finally {
            ctx.variables.clear();
            ctx.variables.putAll(savedScope);
        }

        setLast(returnValue);
        logger.info("Función '" + name + "' ejecutada, retornó: " + returnValue);
        return returnValue;
    }

    @Override
    public Object visitFuncionExpr(proyectoParser.FuncionExprContext node) {
        return visit(node.llamadaFuncion());
    }

    @Override
    public Object visitVariableExpr(proyectoParser.VariableExprContext node) {
        return this.ctx.variables.get(node.variable().ID().getText());
    }

    @Override
    public Object visitNumeroExpr(proyectoParser.NumeroExprContext node) {
        String text = node.numero().getText();
        return text.contains(".") ? Double.parseDouble(text) : Long.parseLong(text);
    }

    @Override
    public Object visitCadenaExpr(proyectoParser.CadenaExprContext node) {
        return fs.expandTokens(stripQuotes(node.cadena().getText()));
    }

    @Override
    public Object visitBooleanoExpr(proyectoParser.BooleanoExprContext node) {
        return node.booleano().getText().equalsIgnoreCase("VERDADERO");
    }

    @Override
    public Object visitListaExpr(proyectoParser.ListaExprContext node) {
        List<Object> values = new ArrayList<>();
        if (node.lista().argumentos() != null) {
            for (proyectoParser.ExpresionContext expr : node.lista().argumentos().expresion()) {
                values.add(visit(expr));
            }
        }
        return values;
    }

    @Override public Object visitParenExpr(proyectoParser.ParenExprContext node) { return visit(node.expresion()); }
    @Override public Object visitNegExpr(proyectoParser.NegExprContext node) { return -toNumber(visit(node.expresion())); }
    @Override public Object visitNotExpr(proyectoParser.NotExprContext node) { return !toBoolean(visit(node.expresion())); }
    @Override public Object visitAndExpr(proyectoParser.AndExprContext node) { return toBoolean(visit(node.expresion(0))) && toBoolean(visit(node.expresion(1))); }
    @Override public Object visitOrExpr(proyectoParser.OrExprContext node) { return toBoolean(visit(node.expresion(0))) || toBoolean(visit(node.expresion(1))); }
    @Override public Object visitSumaExpr(proyectoParser.SumaExprContext node) {
        Object left = visit(node.expresion(0));
        Object right = visit(node.expresion(1));
        if (left instanceof String || right instanceof String) return stringify(left) + stringify(right);
        return toNumber(left) + toNumber(right);
    }
    @Override public Object visitRestaExpr(proyectoParser.RestaExprContext node) { return toNumber(visit(node.expresion(0))) - toNumber(visit(node.expresion(1))); }
    @Override public Object visitMultExpr(proyectoParser.MultExprContext node) { return toNumber(visit(node.expresion(0))) * toNumber(visit(node.expresion(1))); }
    @Override public Object visitDivExpr(proyectoParser.DivExprContext node) { return toNumber(visit(node.expresion(0))) / toNumber(visit(node.expresion(1))); }
    @Override public Object visitModExpr(proyectoParser.ModExprContext node) { return toLong(visit(node.expresion(0))) % toLong(visit(node.expresion(1))); }
    @Override public Object visitMayorExpr(proyectoParser.MayorExprContext node) { return compare(visit(node.expresion(0)), visit(node.expresion(1))) > 0; }
    @Override public Object visitMenorExpr(proyectoParser.MenorExprContext node) { return compare(visit(node.expresion(0)), visit(node.expresion(1))) < 0; }
    @Override public Object visitMayorIgualExpr(proyectoParser.MayorIgualExprContext node) { return compare(visit(node.expresion(0)), visit(node.expresion(1))) >= 0; }
    @Override public Object visitMenorIgualExpr(proyectoParser.MenorIgualExprContext node) { return compare(visit(node.expresion(0)), visit(node.expresion(1))) <= 0; }
    @Override public Object visitIgualExpr(proyectoParser.IgualExprContext node) { return Objects.equals(normalize(visit(node.expresion(0))), normalize(visit(node.expresion(1)))); }
    @Override public Object visitDiferenteExpr(proyectoParser.DiferenteExprContext node) { return !Objects.equals(normalize(visit(node.expresion(0))), normalize(visit(node.expresion(1)))); }

    private void setLast(Object value) {
        this.ctx.ultimoResultado = value;
        if (this.ctx.variables != null) {
            this.ctx.variables.put("ultimoResultado", value);
        }
    }

    private void executeTask(String name) {
        Task task = ctx.tareas.get(name);
        if (task == null) {
            logger.error("Tarea no encontrada: " + name);
            taskLogger.scheduler("Tarea no encontrada: " + name);
            return;
        }
        long startedAt = System.currentTimeMillis();
        runningTask.set(name);
        logger.info("Iniciando tarea: " + name);
        taskLogger.start(name, fs.getWorkingDirectory());
        try {
            visit(task.getBody());
            long duration = System.currentTimeMillis() - startedAt;
            taskLogger.end(name, duration);
            logger.info("Tarea finalizada: " + name + " en " + duration + " ms");
        } catch (RuntimeException ex) {
            taskLogger.error(name, ex);
            logger.error("Error ejecutando tarea: " + name, ex);
            throw ex;
        } finally {
            runningTask.remove();
        }
    }

    private void runScheduledTask(String name) {
        taskLogger.scheduler("Intentando ejecutar tarea programada: " + name);
        try {
            executeTask(name);
            taskLogger.scheduler("Ejecución programada finalizada: " + name);
        } catch (RuntimeException ex) {
            taskLogger.scheduler("La tarea " + name + " falló y se mantendrá programada: " + ex.getMessage());
        }
    }

    private void runOneTimeScheduledTask(String name) {
        taskLogger.scheduler("Intentando ejecutar tarea programada una sola vez: " + name);
        try {
            executeTask(name);
            scheduleStore.deleteSchedule(name);
            scheduledTasks.remove(name);
            scheduledFutures.remove(name);
            if (scheduledTasks.isEmpty()) {
                scheduler.shutdown();
            }
            taskLogger.scheduler("Ejecución única finalizada y eliminada: " + name);
        } catch (RuntimeException ex) {
            taskLogger.scheduler("La tarea única " + name + " falló y se mantiene registrada para diagnóstico: " + ex.getMessage());
        }
    }

    private boolean cancelScheduledTask(String name, boolean deleteFromStore) {
        boolean changed = false;
        ScheduledFuture<?> future = scheduledFutures.remove(name);
        if (future != null) {
            future.cancel(false);
            changed = true;
        }
        if (scheduledTasks.remove(name)) {
            changed = true;
        }
        if (deleteFromStore && scheduleStore.deleteSchedule(name)) {
            startupRegistration.unregister(name);
            taskLogger.scheduler("Programación eliminada: " + name);
            changed = true;
        }
        if (changed && scheduledTasks.isEmpty()) {
            scheduler.shutdown();
        }
        return changed;
    }

    private int cancelAllScheduledTasks() {
        List<ScheduleStore.ScheduleEntry> persisted = scheduleStore.loadSchedules();
        java.util.Set<String> names = new java.util.HashSet<>();
        names.addAll(scheduledTasks);
        for (ScheduleStore.ScheduleEntry entry : persisted) {
            names.add(entry.task);
        }

        for (ScheduledFuture<?> future : scheduledFutures.values()) {
            future.cancel(false);
        }
        scheduledFutures.clear();
        scheduledTasks.clear();
        for (String name : names) {
            startupRegistration.unregister(name);
        }
        int persistedRemoved = scheduleStore.deleteAllSchedules();
        scheduler.shutdown();
        taskLogger.scheduler("Todas las programaciones fueron eliminadas: " + Math.max(names.size(), persistedRemoved));
        return Math.max(names.size(), persistedRemoved);
    }

    private String formatSchedule(ScheduleStore.ScheduleEntry schedule) {
        if ("once".equalsIgnoreCase(schedule.kind)) {
            return schedule.task + " | una vez | " + schedule.fireAt;
        }
        if ("startup".equalsIgnoreCase(schedule.kind)) {
            return schedule.task + " | al iniciar sistema";
        }
        return schedule.task + " | cada " + schedule.periodSeconds + " segundos";
    }

    private void taskEvent(String message) {
        String taskName = runningTask.get();
        if (taskName != null) {
            taskLogger.event(taskName, message);
        }
    }

    private Path toPath(Object value) {
        return fs.pathOf(value);
    }

    private String stringify(Object value) {
        if (value == null) return "";
        if (value instanceof Double d) {
            if (d == Math.floor(d) && !Double.isInfinite(d)) return String.valueOf(d.longValue());
        }
        return value.toString();
    }

    private String stripQuotes(String text) {
        if (text == null || text.length() < 2) return text;
        if ((text.startsWith("\"") && text.endsWith("\"")) || (text.startsWith("'") && text.endsWith("'"))) {
            String inner = text.substring(1, text.length() - 1);
            StringBuilder sb = new StringBuilder(inner.length());
            for (int i = 0; i < inner.length(); i++) {
                if (inner.charAt(i) == '\\' && i + 1 < inner.length()) {
                    char next = inner.charAt(i + 1);
                    switch (next) {
                        case '"'  -> { sb.append('"');  i++; }
                        case '\'' -> { sb.append('\''); i++; }
                        case 'n'  -> { sb.append('\n'); i++; }
                        case '\\' -> { sb.append('\\'); i++; }
                        default   -> { sb.append('\\').append(next); i++; }
                    }
                } else {
                    sb.append(inner.charAt(i));
                }
            }
            return sb.toString();
        }
        return text;
    }

    private String formatValue(Object value) {
        if (value instanceof Double d) {
            if (d == Math.floor(d) && !Double.isInfinite(d)) return String.valueOf(d.longValue());
            return d.toString();
        }
        return value == null ? "null" : value.toString();
    }

    private boolean toBoolean(Object value) {
        if (value instanceof Boolean b) return b;
        if (value instanceof Number n) return n.doubleValue() != 0d;
        return value != null && Boolean.parseBoolean(value.toString());
    }

    private long toLong(Object value) {
        if (value == null) return 0L;
        if (value instanceof Number n) return n.longValue();
        try {
            return Long.parseLong(String.valueOf(value));
        } catch (NumberFormatException ex) {
            logger.info("toLong: no se pudo convertir '" + value + "' a long, devolviendo 0");
            return 0L;
        }
    }

    private double toNumber(Object value) {
        if (value == null) return 0d;
        if (value instanceof Number n) return n.doubleValue();
        try {
            return Double.parseDouble(String.valueOf(value));
        } catch (NumberFormatException ex) {
            logger.info("toNumber: no se pudo convertir '" + value + "' a número, devolviendo 0");
            return 0d;
        }
    }

    private Object normalize(Object value) {
        if (value instanceof Number n) {
            return n.doubleValue();
        }
        return value;
    }

    private int compare(Object left, Object right) {
        if (left instanceof Number || right instanceof Number) {
            return Double.compare(toNumber(left), toNumber(right));
        }
        return String.valueOf(left).compareTo(String.valueOf(right));
    }

    private java.util.function.Predicate<Path> buildFilter(proyectoParser.FiltrosContext filtros) {
        if (filtros == null || filtros.filtro().isEmpty()) {
            return p -> true;
        }
        java.util.function.Predicate<Path> combined = p -> true;
        for (proyectoParser.FiltroContext filtro : filtros.filtro()) {
            combined = combined.and(buildSingleFilter(filtro));
        }
        return combined;
    }

    private java.util.function.Predicate<Path> buildSingleFilter(proyectoParser.FiltroContext filtro) {
        if (filtro.EXTENSION() != null && filtro.cadena() != null) {
            return FilterUtils.byExtension(stripQuotes(filtro.cadena().getText()));
        } else if (filtro.NOMBRE() != null && filtro.cadena() != null) {
            return FilterUtils.nameContains(stripQuotes(filtro.cadena().getText()));
        } else if (filtro.PREFIJO() != null && filtro.cadena() != null) {
            return FilterUtils.byPrefix(stripQuotes(filtro.cadena().getText()));
        } else if (filtro.SUFIJO() != null && filtro.cadena() != null) {
            return FilterUtils.bySuffix(stripQuotes(filtro.cadena().getText()));
        } else if (filtro.MAYOR() != null && filtro.expresion() != null) {
            long bytes = toBytes(toNumber(visit(filtro.expresion())), filtro.unidadTamano().getText());
            return FilterUtils.sizeComparator(bytes, ">");
        } else if (filtro.MENOR() != null && filtro.expresion() != null) {
            long bytes = toBytes(toNumber(visit(filtro.expresion())), filtro.unidadTamano().getText());
            return FilterUtils.sizeComparator(bytes, "<");
        } else if (filtro.ANTIGUOS() != null && filtro.expresion() != null) {
            long days = toLong(visit(filtro.expresion()));
            return FilterUtils.olderThanDays(days);
        } else if (filtro.RECIENTES() != null && filtro.expresion() != null) {
            long days = toLong(visit(filtro.expresion()));
            return FilterUtils.recentThanDays(days);
        }
        return p -> true;
    }

    private long toBytes(double amount, String unit) {
        String normalized = unit == null ? "" : unit.toUpperCase();
        return switch (normalized) {
            case "GB" -> (long) (amount * 1024 * 1024 * 1024);
            case "MB" -> (long) (amount * 1024 * 1024);
            case "KB" -> (long) (amount * 1024);
            case "BYTES" -> (long) amount;
            default -> (long) amount;
        };
    }

    private TimeUnit toTimeUnit(String text) {
        String normalized = text.toLowerCase();
        if (normalized.contains("seg")) return TimeUnit.SECONDS;
        if (normalized.contains("min")) return TimeUnit.MINUTES;
        if (normalized.contains("hora")) return TimeUnit.HOURS;
        if (normalized.contains("dia")) return TimeUnit.DAYS;
        return TimeUnit.SECONDS;
    }
}
