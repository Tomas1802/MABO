package scheduler;

import logs.LoggerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class StartupRegistrationService {
    private final LoggerService logger;

    public StartupRegistrationService(LoggerService logger) {
        this.logger = logger;
    }

    public boolean register(String taskName, Path scriptPath) {
        if (scriptPath == null) {
            logger.info("No se registró inicio del sistema: script desconocido");
            return false;
        }
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        try {
            if (os.contains("win")) {
                return registerWindows(taskName, scriptPath);
            }
            return registerLinux(taskName, scriptPath);
        } catch (Exception ex) {
            logger.info("No se pudo registrar inicio del sistema para " + taskName + ": " + ex.getMessage());
            return false;
        }
    }

    public boolean unregister(String taskName) {
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        try {
            if (os.contains("win")) {
                Process p = new ProcessBuilder("schtasks", "/Delete", "/F", "/TN", "MABO_" + taskName).start();
                boolean ok = p.waitFor() == 0;
                logger.info("Eliminación Windows startup " + taskName + ": " + ok);
                return ok;
            }
            Path desktopFile = Path.of(System.getProperty("user.home"), ".config", "autostart", "mabo-" + taskName + ".desktop");
            Files.deleteIfExists(desktopFile);
            logger.info("Eliminación Linux startup: " + desktopFile);
            return true;
        } catch (Exception ex) {
            logger.info("No se pudo eliminar inicio del sistema para " + taskName + ": " + ex.getMessage());
            return false;
        }
    }

    private boolean registerWindows(String taskName, Path scriptPath) throws IOException, InterruptedException {
        String javaExe = Path.of(System.getProperty("java.home"), "bin", "java.exe").toString();
        String classPath = System.getProperty("java.class.path");
        String command = "\"" + javaExe + "\" -cp \"" + classPath + "\" Main \"" + scriptPath.toAbsolutePath() + "\"";
        Process p = new ProcessBuilder("schtasks", "/Create", "/F", "/SC", "ONLOGON",
                "/TN", "MABO_" + taskName, "/TR", command).start();
        boolean ok = p.waitFor() == 0;
        logger.info("Registro Windows startup " + taskName + ": " + ok);
        return ok;
    }

    private boolean registerLinux(String taskName, Path scriptPath) throws IOException {
        Path autostart = Path.of(System.getProperty("user.home"), ".config", "autostart");
        Files.createDirectories(autostart);
        Path desktopFile = autostart.resolve("mabo-" + taskName + ".desktop");
        String javaExe = Path.of(System.getProperty("java.home"), "bin", "java").toString();
        String classPath = System.getProperty("java.class.path");
        String content = """
                [Desktop Entry]
                Type=Application
                Name=MABO %s
                Exec=%s -cp %s Main %s
                X-GNOME-Autostart-enabled=true
                """.formatted(taskName, javaExe, classPath, scriptPath.toAbsolutePath());
        Files.writeString(desktopFile, content);
        logger.info("Registro Linux startup creado: " + desktopFile);
        return true;
    }
}
