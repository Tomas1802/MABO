package dsl;

import gen.proyectoLexer;
import gen.proyectoParser;
import logs.LoggerService;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import semantic.ExecutionContext;
import visitors.ExecutionVisitor;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
