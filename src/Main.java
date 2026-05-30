//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import gen.proyectoLexer;
import gen.proyectoParser;
import logs.LoggerService;
import semantic.ExecutionContext;
import visitors.ExecutionVisitor;
import visitors.SemanticVisitor;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException {
        String scriptPath = args.length > 0 ? args[0] : "src/input_maquina.txt";
        Path p = Paths.get(scriptPath);
        if (!Files.exists(p)) {
            System.err.println("Script no encontrado: " + p.toAbsolutePath());
            return;
        }

        LoggerService logger = new LoggerService("logs/app.log");
        System.out.println("=== DSL de automatizacion ===");
        System.out.println("Script: " + p.toAbsolutePath());
        System.out.println("Evidencia: " + logger.getLogFile().toAbsolutePath());
        logger.info("Iniciando ejecución del script: " + p.toAbsolutePath());

        // Contexto compartido
        ExecutionContext ctx = new ExecutionContext();

        // Semantic analysis
        CharStream input = CharStreams.fromPath(p);
        proyectoLexer lexer = new proyectoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        proyectoParser parser = new proyectoParser(tokens);
        ParseTree tree = parser.programa();

        SemanticVisitor sem = new SemanticVisitor(ctx);
        sem.validar(tree);
        System.out.println("Analisis semantico completado");

        // Execution
        ExecutionVisitor exec = new ExecutionVisitor(ctx, logger, p);
        exec.visit(tree);
        // restore persisted schedules (if any)
        exec.restoreSchedules();
        System.out.println("Ejecucion completada");

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
}
