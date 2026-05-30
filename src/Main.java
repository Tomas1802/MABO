import gen.proyectoLexer;
import gen.proyectoParser;
import logs.LoggerService;
import semantic.ExecutionContext;
import visitors.ExecutionVisitor;
import visitors.SemanticVisitor;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        LoggerService logger = new LoggerService("logs/app.log");
        ExecutionContext ctx = new ExecutionContext();
        ExecutionVisitor exec = new ExecutionVisitor(ctx, logger);

        if (args.length == 0) {
            runInteractive(ctx, exec, logger);
            return;
        }

        if ("-c".equals(args[0]) || "--command".equals(args[0])) {
            if (args.length < 2) {
                System.err.println("Uso: mabo -c \"Mostrar \\\"Hola\\\"\"");
                return;
            }
            runSource(String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length)), ctx, exec, logger);
            return;
        }

        runScript(Paths.get(args[0]), ctx, logger);
    }

    private static void runScript(Path p, ExecutionContext ctx, LoggerService logger) throws IOException {
        if (!Files.exists(p)) {
            System.err.println("Script no encontrado: " + p.toAbsolutePath());
            return;
        }

        System.out.println("=== DSL de automatizacion ===");
        System.out.println("Script: " + p.toAbsolutePath());
        System.out.println("Evidencia: " + logger.getLogFile().toAbsolutePath());
        logger.info("Iniciando ejecución del script: " + p.toAbsolutePath());

        ExecutionVisitor exec = new ExecutionVisitor(ctx, logger, p);
        runSource(Files.readString(p), ctx, exec, logger);
        exec.restoreSchedules();

        logger.info("Ejecución finalizada");
        System.out.println("Logs guardados en logs/app.log");

        if (exec.hayTareasProgramadas()) {
            System.out.println("-----------------------------------------------");
            System.out.println("Tareas programadas activas. El proceso permanece");
            System.out.println("en ejecucion para cumplir los horarios definidos.");
            System.out.println("Presiona Ctrl+C para detener el scheduler.");
            System.out.println("-----------------------------------------------");
        }

        Path summaryFile = Paths.get(System.getProperty("user.home"), "Documents", "DSLDemo", "evidencia-ejecucion.txt");
        Files.createDirectories(summaryFile.getParent());
        String summary = String.format("%s | script=%s | log=%s%n",
            LocalDateTime.now(), p.toAbsolutePath(), logger.getLogFile().toAbsolutePath());
        Files.writeString(summaryFile, summary, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private static void runInteractive(ExecutionContext ctx, ExecutionVisitor exec, LoggerService logger) {
        System.out.println("=== MABO interactivo ===");
        System.out.println("Escribe comandos DSL. Usa 'salir' para terminar y 'ayuda' para ver ejemplos.");

        Scanner scanner = new Scanner(System.in);
        StringBuilder buffer = new StringBuilder();
        int blockDepth = 0;

        while (true) {
            System.out.print(blockDepth > 0 ? "... " : "mabo> ");
            if (!scanner.hasNextLine()) break;

            String line = scanner.nextLine();
            String trimmed = line.trim();
            if (blockDepth == 0 && (trimmed.equalsIgnoreCase("salir") || trimmed.equalsIgnoreCase("exit"))) {
                break;
            }
            if (blockDepth == 0 && trimmed.equalsIgnoreCase("ayuda")) {
                printHelp();
                continue;
            }
            if (trimmed.isEmpty() && blockDepth == 0) {
                continue;
            }

            buffer.append(line).append(System.lineSeparator());
            blockDepth += blockDelta(trimmed);

            if (blockDepth <= 0) {
                String source = buffer.toString();
                buffer.setLength(0);
                blockDepth = 0;
                try {
                    runSource(source, ctx, exec, logger);
                } catch (Exception ex) {
                    System.err.println("Error: " + ex.getMessage());
                    logger.error("Error interactivo", ex);
                }
            }
        }
    }

    private static void runSource(String source, ExecutionContext ctx, ExecutionVisitor exec, LoggerService logger) {
        proyectoParser parser = parserFor(CharStreams.fromString(source));
        ParseTree tree = parser.programa();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new RuntimeException("El comando contiene errores de sintaxis");
        }

        SemanticVisitor sem = new SemanticVisitor(ctx);
        sem.validar(tree);
        exec.visit(tree);
        logger.info("Comando ejecutado desde entrada directa");
    }

    private static proyectoParser parserFor(CharStream input) {
        proyectoLexer lexer = new proyectoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        proyectoParser parser = new proyectoParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                System.err.println("Sintaxis línea " + line + ":" + charPositionInLine + " " + msg);
            }
        });
        return parser;
    }

    private static int blockDelta(String trimmed) {
        String lower = trimmed.toLowerCase();
        int delta = 0;
        if (lower.startsWith("tarea ") || lower.startsWith("funcion ") || lower.startsWith("si ")
                || lower.startsWith("mientras ") || lower.startsWith("para cada ") || lower.equals("intentar")) {
            delta++;
        }
        if (lower.equals("fin") || lower.equals("finfuncion") || lower.equals("finsi")
                || lower.equals("finmientras") || lower.equals("finpara") || lower.equals("finintentar")) {
            delta--;
        }
        return delta;
    }

    private static void printHelp() {
        System.out.println("Ejemplos:");
        System.out.println("  Variable base = \"%USERPROFILE%/Documents/DSLDemo\"");
        System.out.println("  Crear Carpeta base + \"/prueba\"");
        System.out.println("  Listar Contenido base");
        System.out.println("  Simular Ejecutar PowerShell \"Write-Output 'hola'\"");
        System.out.println("Bloques multilinea:");
        System.out.println("  Tarea Demo:");
        System.out.println("      Mostrar \"hola\"");
        System.out.println("  Fin");
        System.out.println("  Ejecutar Tarea Demo");
    }
}
