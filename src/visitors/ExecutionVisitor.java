package visitors;

import filesystem.FileSystemService;
import security.SecurityValidator;
import gen.proyectoBaseVisitor;
import gen.proyectoLexer;
import gen.proyectoParser;
import logs.LoggerService;
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
import java.util.concurrent.TimeUnit;

public class ExecutionVisitor extends proyectoBaseVisitor<Object> {
    private final ExecutionContext ctx;
    private final FileSystemService fs;
    private final LoggerService logger;
    private final ShellCommandService shell;
    private final StartupRegistrationService startupRegistration;
    private final SchedulerService scheduler = new SchedulerService();
    private final ScheduleStore scheduleStore = new ScheduleStore();
    private final java.util.Set<String> scheduledTasks = new java.util.HashSet<>();
    private final java.util.Set<String> importedFiles = new java.util.HashSet<>();
    private final Path scriptPath;

    public boolean hayTareasProgramadas() {
        return !scheduledTasks.isEmpty();
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
        return value;
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
        executeTask(name);
        return null;
    }

    @Override
    public Object visitProgramacion(proyectoParser.ProgramacionContext node) {
        String taskName = node.ID().getText();
        if (node.CADA() != null) {
            long amount = parseNumero(node.numero());
            TimeUnit unit = toTimeUnit(node.tiempo().getText());
            if (amount <= 0) {
                logger.info("Programación ignorada para " + taskName + ": periodo inválido " + amount);
                return null;
            }
            scheduler.scheduleAtFixedRate(() -> executeTask(taskName), 0, amount, unit);
            scheduledTasks.add(taskName);
            // persist schedule in seconds
            long seconds = unit.toSeconds(amount);
            scheduleStore.saveSchedule(taskName, seconds);
            logger.info("Programada tarea " + taskName + " cada " + amount + " " + unit);
            executeTask(taskName);
        } else if (node.LAS() != null) {
            LocalTime time = parseTime(stripQuotes(node.cadena().getText()));
            long initialDelay = secondsUntil(time);
            scheduler.scheduleAtFixedRate(() -> executeTask(taskName), initialDelay, 1, TimeUnit.DAYS);
            scheduledTasks.add(taskName);
            scheduleStore.saveDailyTime(taskName, time.toString());
            logger.info("Programada tarea " + taskName + " a las " + time);
        } else {
            boolean registered = startupRegistration.register(taskName, scriptPath);
            scheduleStore.saveStartupSchedule(taskName);
            scheduledTasks.add(taskName);
            logger.info("Ejecución al iniciar sistema de tarea: " + taskName + " registrada=" + registered);
            executeTask(taskName);
        }
        return null;
    }

    public void restoreSchedules() {
        java.util.List<ScheduleStore.ScheduleEntry> list = scheduleStore.loadSchedules();
        for (ScheduleStore.ScheduleEntry se : list) {
            if (scheduledTasks.contains(se.task)) continue; // already scheduled in this run
            if (!ctx.tareas.containsKey(se.task)) {
                logger.info("No existe tarea para reprogramar: " + se.task);
                continue;
            }
            if ("daily".equalsIgnoreCase(se.kind)) {
                LocalTime time = parseTime(se.time);
                scheduler.scheduleAtFixedRate(() -> executeTask(se.task), secondsUntil(time), 1, TimeUnit.DAYS);
                scheduledTasks.add(se.task);
                logger.info("Restaurada tarea diaria: " + se.task + " a las " + time);
                continue;
            }
            if ("startup".equalsIgnoreCase(se.kind)) {
                startupRegistration.register(se.task, scriptPath);
                scheduledTasks.add(se.task);
                logger.info("Restaurada tarea de inicio del sistema: " + se.task);
                continue;
            }
            if (se.periodSeconds <= 0) {
                logger.info("Entrada de schedule inválida ignorada para " + se.task + ": " + se.periodSeconds + "s");
                continue;
            }
            scheduler.scheduleAtFixedRate(() -> executeTask(se.task), 0, se.periodSeconds, java.util.concurrent.TimeUnit.SECONDS);
            scheduledTasks.add(se.task);
            logger.info("Restaurada tarea programada: " + se.task + " cada " + se.periodSeconds + "s");
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
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.withHour(time.getHour()).withMinute(time.getMinute()).withSecond(0).withNano(0);
        if (!next.isAfter(now)) {
            next = next.plusDays(1);
        }
        return Math.max(1L, Duration.between(now, next).toSeconds());
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
        return result;
    }

    @Override
    public Object visitEjecutarLinux(proyectoParser.EjecutarLinuxContext node) {
        Object result = shell.execute("linux", stringify(visit(node.expresion())), this.ctx.modoSimulacion);
        setLast(result);
        System.out.println(result);
        return result;
    }

    @Override
    public Object visitCrearArchivo(proyectoParser.CrearArchivoContext node) {
        fs.createFile(toPath(visit(node.expresion())), this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitCrearCarpeta(proyectoParser.CrearCarpetaContext node) {
        fs.createDirectories(toPath(visit(node.expresion())), this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitLeerArchivo(proyectoParser.LeerArchivoContext node) {
        String content = fs.readText(toPath(visit(node.expresion())));
        setLast(content);
        System.out.println(content);
        logger.info("LeerArchivo ejecutado");
        return content;
    }

    @Override
    public Object visitEscribirArchivo(proyectoParser.EscribirArchivoContext node) {
        String content = stringify(visit(node.expresion(0)));
        Path path = toPath(visit(node.expresion(1)));
        fs.writeText(path, content, false, this.ctx.modoSimulacion);
        setLast(content);
        return content;
    }

    @Override
    public Object visitAnexarArchivo(proyectoParser.AnexarArchivoContext node) {
        String content = stringify(visit(node.expresion(0)));
        Path path = toPath(visit(node.expresion(1)));
        fs.writeText(path, content, true, this.ctx.modoSimulacion);
        setLast(content);
        return content;
    }

    @Override
    public Object visitEliminarArchivo(proyectoParser.EliminarArchivoContext node) {
        fs.delete(toPath(visit(node.expresion())), this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitEliminarCarpeta(proyectoParser.EliminarCarpetaContext node) {
        fs.delete(toPath(visit(node.expresion())), this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitCopiarArchivo(proyectoParser.CopiarArchivoContext node) {
        fs.copy(toPath(visit(node.expresion(0))), toPath(visit(node.expresion(1))), this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitCopiarCarpeta(proyectoParser.CopiarCarpetaContext node) {
        fs.copy(toPath(visit(node.expresion(0))), toPath(visit(node.expresion(1))), this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitMoverArchivo(proyectoParser.MoverArchivoContext node) {
        fs.move(toPath(visit(node.expresion(0))), toPath(visit(node.expresion(1))), this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitMoverCarpeta(proyectoParser.MoverCarpetaContext node) {
        fs.move(toPath(visit(node.expresion(0))), toPath(visit(node.expresion(1))), this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitRenombrarArchivo(proyectoParser.RenombrarArchivoContext node) {
        fs.move(toPath(visit(node.expresion(0))), toPath(visit(node.expresion(1))), this.ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitRenombrarCarpeta(proyectoParser.RenombrarCarpetaContext node) {
        fs.move(toPath(visit(node.expresion(0))), toPath(visit(node.expresion(1))), this.ctx.modoSimulacion);
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
        return results;
    }

    @Override
    public Object visitListarContenido(proyectoParser.ListarContenidoContext node) {
        List<Path> children = fs.listChildren(toPath(visit(node.expresion())));
        setLast(children);
        children.forEach(System.out::println);
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
        fs.compress(source, target, ctx.modoSimulacion);
        if (!ctx.modoSimulacion) System.out.println("Comprimido: " + source + " -> " + target);
        return null;
    }

    @Override
    public Object visitDescomprimir(proyectoParser.DescomprimirContext node) {
        Path source = toPath(visit(node.expresion(0)));
        Path target = toPath(visit(node.expresion(1)));
        fs.decompress(source, target, ctx.modoSimulacion);
        if (!ctx.modoSimulacion) System.out.println("Descomprimido: " + source + " -> " + target);
        return null;
    }

    @Override
    public Object visitCambiarPermisos(proyectoParser.CambiarPermisosContext node) {
        Path path = toPath(visit(node.expresion(0)));
        String permissions = stringify(visit(node.expresion(1)));
        fs.changePermissions(path, permissions, ctx.modoSimulacion);
        return null;
    }

    @Override
    public Object visitCrearBackup(proyectoParser.CrearBackupContext node) {
        fs.copy(toPath(visit(node.expresion(0))), toPath(visit(node.expresion(1))), this.ctx.modoSimulacion);
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

        // Guardar scope actual y crear scope de la función
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
            return;
        }
        visit(task.getBody());
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
                        case 't'  -> { sb.append('\t'); i++; }
                        case 'r'  -> { sb.append('\r'); i++; }
                        case '\\' -> { sb.append('\\'); i++; }
                        default   -> sb.append('\\');
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
        } else if (filtro.MAYORES() != null && filtro.expresion() != null) {
            long sizeMb = toLong(visit(filtro.expresion()));
            return FilterUtils.sizeComparator(sizeMb, ">");
        } else if (filtro.MENORES() != null && filtro.expresion() != null) {
            long sizeMb = toLong(visit(filtro.expresion()));
            return FilterUtils.sizeComparator(sizeMb, "<");
        } else if (filtro.ANTIGUOS() != null && filtro.expresion() != null) {
            long days = toLong(visit(filtro.expresion()));
            return FilterUtils.olderThanDays(days);
        } else if (filtro.RECIENTES() != null && filtro.expresion() != null) {
            long days = toLong(visit(filtro.expresion()));
            return FilterUtils.recentThanDays(days);
        }
        return p -> true;
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
