package dsl;

import gen.proyectoLexer;
import gen.proyectoParser;
import logs.LoggerService;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import scheduler.ScheduleStore;
import semantic.ExecutionContext;
import visitors.ExecutionVisitor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExecutionFeatureTest {
    @Test
    void changesDirectoryUsingWindowsBackslashPathAndListsCurrentDirectory() throws Exception {
        Path base = Files.createTempDirectory(Path.of(System.getProperty("user.home")), "mabo-nav-");
        try {
            String script = """
                    Ir A "%s"
                    Listar Contenido
                    """.formatted(base.toString());

            proyectoLexer lexer = new proyectoLexer(CharStreams.fromString(script));
            proyectoParser parser = new proyectoParser(new CommonTokenStream(lexer));
            ExecutionVisitor visitor = new ExecutionVisitor(
                    new ExecutionContext(),
                    new LoggerService(Files.createTempFile("mabo-test", ".log").toString())
            );

            visitor.visit(parser.programa());

            assertEquals(base.toAbsolutePath().normalize(), visitor.getCurrentDirectory());
        } finally {
            Files.deleteIfExists(base);
        }
    }

    @Test
    void taskDeleteWithSinConfirmarDoesNotPromptAndDeletesFile() throws Exception {
        Path base = Files.createTempDirectory(Path.of(System.getProperty("user.home")), "mabo-task-delete-");
        Path file = Files.writeString(base.resolve("delete.xml"), "delete me");
        try {
            String script = """
                    Tarea LimpiarArchivosXML:
                        Eliminar Archivo "%s" Sin Confirmar
                    Fin
                    Ejecutar Tarea LimpiarArchivosXML
                    """.formatted(file.toString());

            proyectoLexer lexer = new proyectoLexer(CharStreams.fromString(script));
            proyectoParser parser = new proyectoParser(new CommonTokenStream(lexer));
            ExecutionVisitor visitor = new ExecutionVisitor(
                    new ExecutionContext(),
                    new LoggerService(Files.createTempFile("mabo-test", ".log").toString())
            );

            visitor.visit(parser.programa());

            assertFalse(Files.exists(file));
        } finally {
            Files.deleteIfExists(file);
            Files.deleteIfExists(base);
        }
    }

    @Test
    void taskExecutionWritesDetailedTaskLog() throws Exception {
        Path logDir = Files.createTempDirectory("mabo-task-logs-");
        String previousLogDir = System.getProperty("mabo.log.dir");
        System.setProperty("mabo.log.dir", logDir.toString());

        try {
            String script = """
                    Tarea Diagnostico:
                        Mostrar "inicio"
                        Listar Contenido
                    Fin
                    Ejecutar Tarea Diagnostico
                    """;

            proyectoLexer lexer = new proyectoLexer(CharStreams.fromString(script));
            proyectoParser parser = new proyectoParser(new CommonTokenStream(lexer));
            ExecutionVisitor visitor = new ExecutionVisitor(
                    new ExecutionContext(),
                    new LoggerService(Files.createTempFile("mabo-test", ".log").toString())
            );

            visitor.visit(parser.programa());

            try (var files = Files.list(logDir)) {
                Path logFile = files
                        .filter(path -> path.getFileName().toString().startsWith("Diagnostico-"))
                        .findFirst()
                        .orElseThrow();
                String log = Files.readString(logFile);
                assertTrue(log.contains("[START] Tarea Diagnostico iniciada"));
                assertTrue(log.contains("Mostrar: inicio"));
                assertTrue(log.contains("Listar contenido"));
                assertTrue(log.contains("[END] Tarea Diagnostico finalizada"));
            }
        } finally {
            if (previousLogDir == null) {
                System.clearProperty("mabo.log.dir");
            } else {
                System.setProperty("mabo.log.dir", previousLogDir);
            }
            try (var files = Files.list(logDir)) {
                files.forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (Exception ignored) {
                    }
                });
            }
            Files.deleteIfExists(logDir);
        }
    }

    @Test
    void canListAndDeletePersistedSchedules() throws Exception {
        Path scheduleFile = Files.createTempFile("mabo-schedules", ".ndjson");
        String previousScheduleFile = System.getProperty("mabo.schedule.file");
        System.setProperty("mabo.schedule.file", scheduleFile.toString());

        try {
            ScheduleStore store = new ScheduleStore();
            store.saveIntervalSchedule("LimpiarXML", 3600);
            store.saveOneTimeSchedule("UnaVez", LocalDateTime.now().plusHours(1));

            String script = """
                    Listar Tareas Programadas
                    Eliminar Tarea Programada LimpiarXML
                    """;

            proyectoLexer lexer = new proyectoLexer(CharStreams.fromString(script));
            proyectoParser parser = new proyectoParser(new CommonTokenStream(lexer));
            ExecutionVisitor visitor = new ExecutionVisitor(
                    new ExecutionContext(),
                    new LoggerService(Files.createTempFile("mabo-test", ".log").toString())
            );

            visitor.visit(parser.programa());

            var schedules = store.loadSchedules();
            assertEquals(1, schedules.size());
            assertEquals("UnaVez", schedules.get(0).task);
        } finally {
            if (previousScheduleFile == null) {
                System.clearProperty("mabo.schedule.file");
            } else {
                System.setProperty("mabo.schedule.file", previousScheduleFile);
            }
            Files.deleteIfExists(scheduleFile);
        }
    }
}
