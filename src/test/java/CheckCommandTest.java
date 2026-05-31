import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckCommandTest {
    @Test
    void checkCommandValidatesMaboFileWithoutExecutingIt() throws Exception {
        Path base = Files.createTempDirectory(Path.of(System.getProperty("user.home")), "mabo-check-");
        Path target = base.resolve("should-not-exist.txt");
        Path script = Files.writeString(base.resolve("validar.mabo"), """
                Variable base = "%s"
                Tarea Demo:
                    Escribir "no ejecutar" En "%s"
                Fin
                """.formatted(base.toString(), target.toString()));

        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            Main.main(new String[] {"--check", script.toString()});
        } finally {
            System.setOut(originalOut);
            Files.deleteIfExists(script);
            Files.deleteIfExists(target);
            Files.deleteIfExists(base);
        }

        assertTrue(out.toString().contains("OK:"));
        assertTrue(Files.notExists(target));
    }
}
