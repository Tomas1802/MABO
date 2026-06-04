package filesystem;

import exceptions.ExecutionException;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileSystemServiceTest {
    @Test
    void resolvesRelativePathsFromCurrentWorkingDirectory() throws Exception {
        FileSystemService fs = new FileSystemService();
        Path base = Files.createTempDirectory(Path.of(System.getProperty("user.home")), "mabo-cwd-");
        try {
            Path child = Files.createDirectory(base.resolve("sub"));

            fs.changeWorkingDirectory(base);

            assertEquals(child.toAbsolutePath().normalize(), fs.pathOf("sub"));
        } finally {
            Files.deleteIfExists(base.resolve("sub"));
            Files.deleteIfExists(base);
        }
    }

    @Test
    void acceptsWindowsBackslashPathsWithoutTreatingTAsTab() {
        FileSystemService fs = new FileSystemService();
        Path path = fs.pathOf("C:\\Users\\tparr\\Documents\\TOMAS");

        assertEquals(Path.of("C:\\Users\\tparr\\Documents\\TOMAS").toAbsolutePath().normalize(), path);
    }

    @Test
    void treatsSingleLeadingSlashAsRelativeOnWindows() throws Exception {
        FileSystemService fs = new FileSystemService();
        Path base = Files.createTempDirectory(Path.of(System.getProperty("user.home")), "mabo-root-relative-");
        try {
            fs.changeWorkingDirectory(base);

            assertEquals(base.resolve("PRUEBA").toAbsolutePath().normalize(), fs.pathOf("/PRUEBA"));
            assertEquals(base.resolve("PRUEBA").toAbsolutePath().normalize(), fs.pathOf("\\PRUEBA"));
        } finally {
            Files.deleteIfExists(base);
        }
    }

    @Test
    void deletesWithoutPromptWhenConfirmationIsSkipped() throws Exception {
        FileSystemService fs = new FileSystemService();
        Path base = Files.createTempDirectory(Path.of(System.getProperty("user.home")), "mabo-delete-");
        Path file = Files.writeString(base.resolve("temp.tmp"), "delete me");
        try {
            fs.delete(file, false, true);

            assertFalse(Files.exists(file));
        } finally {
            Files.deleteIfExists(file);
            Files.deleteIfExists(base);
        }
    }

    @Test
    void refusesToCopyDirectoryInsideItself() throws Exception {
        FileSystemService fs = new FileSystemService();
        Path base = Files.createTempDirectory(Path.of(System.getProperty("user.home")), "mabo-copy-loop-");
        try {
            Files.writeString(base.resolve("nota.txt"), "contenido");
            fs.changeWorkingDirectory(base);

            assertThrows(ExecutionException.class, () -> fs.copy(fs.pathOf("."), fs.pathOf("/backup"), false));
            assertFalse(Files.exists(base.resolve("backup").resolve("backup")));
        } finally {
            deleteIfExists(base.resolve("backup").resolve("nota.txt"));
            deleteIfExists(base.resolve("backup"));
            deleteIfExists(base.resolve("nota.txt"));
            deleteIfExists(base);
        }
    }

    private static void deleteIfExists(Path path) throws Exception {
        Files.deleteIfExists(path);
    }
}
