package scheduler;

import logs.LoggerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                String safeName = safeTaskName(taskName);
                Process p = new ProcessBuilder("schtasks", "/Delete", "/F", "/TN", "MABO_" + safeName).start();
                boolean ok = p.waitFor() == 0;
                boolean fallbackDeleted = unregisterWindowsStartupFolder(safeName);
                logger.info("Eliminación Windows startup " + taskName + ": " + ok + " fallback=" + fallbackDeleted);
                return ok || fallbackDeleted;
            }
            Path desktopFile = Path.of(System.getProperty("user.home"), ".config", "autostart", "mabo-" + safeTaskName(taskName) + ".desktop");
            Files.deleteIfExists(desktopFile);
            logger.info("Eliminación Linux startup: " + desktopFile);
            return true;
        } catch (Exception ex) {
            logger.info("No se pudo eliminar inicio del sistema para " + taskName + ": " + ex.getMessage());
            return false;
        }
    }

    private boolean registerWindows(String taskName, Path scriptPath) throws IOException, InterruptedException {
        String safeName = safeTaskName(taskName);
        Path wrapper = createWindowsWrapper(safeName, scriptPath);
        String command = "\"" + wrapper + "\"";
        Process p = new ProcessBuilder("schtasks", "/Create", "/F", "/SC", "ONLOGON",
                "/TN", "MABO_" + safeName, "/TR", command).start();
        String output = new String(p.getInputStream().readAllBytes());
        String error = new String(p.getErrorStream().readAllBytes());
        boolean ok = p.waitFor() == 0;
        logger.info("Registro Windows startup " + taskName + ": " + ok + " cmd=" + command
                + " stdout=" + output.strip() + " stderr=" + error.strip());
        if (!ok && error.toLowerCase(Locale.ROOT).contains("acceso denegado")) {
            Path fallback = registerWindowsStartupFolder(safeName, wrapper);
            logger.info("Registro Windows startup fallback creado: " + fallback);
            return true;
        }
        return ok;
    }

    private Path createWindowsWrapper(String safeName, Path scriptPath) throws IOException {
        Path root = resolveMaboRoot();
        Path startupDir = root.resolve("startup");
        Path logDir = root.resolve("logs").resolve("startup");
        Files.createDirectories(startupDir);
        Files.createDirectories(logDir);

        Path wrapper = startupDir.resolve("MABO_" + safeName + ".cmd");
        Path outLog = logDir.resolve("MABO_" + safeName + ".log");
        String maboBat = root.resolve("bin").resolve("mabo.bat").toString();
        String fallbackJava = Path.of(System.getProperty("java.home"), "bin", "java.exe").toString();
        String classPath = System.getProperty("java.class.path");
        String script = scriptPath.toAbsolutePath().normalize().toString();
        String content = """
                @echo off
                echo ==== %%DATE%% %%TIME%% ==== >> "%s"
                if exist "%s" (
                  call "%s" "%s" >> "%s" 2>>&1
                ) else (
                  "%s" -cp "%s" Main "%s" >> "%s" 2>>&1
                )
                echo exitCode=%%ERRORLEVEL%% >> "%s"
                """.formatted(outLog, maboBat, maboBat, script, outLog,
                fallbackJava, classPath, script, outLog, outLog);
        Files.writeString(wrapper, content);
        logger.info("Wrapper Windows startup creado: " + wrapper + " log=" + outLog);
        return wrapper;
    }

    private Path registerWindowsStartupFolder(String safeName, Path wrapper) throws IOException {
        String appData = System.getenv("APPDATA");
        Path startupDir = appData == null || appData.isBlank()
                ? Paths.get(System.getProperty("user.home"), "AppData", "Roaming", "Microsoft", "Windows", "Start Menu", "Programs", "Startup")
                : Paths.get(appData, "Microsoft", "Windows", "Start Menu", "Programs", "Startup");
        Files.createDirectories(startupDir);
        Path launcher = startupDir.resolve("MABO_" + safeName + ".cmd");
        String content = """
                @echo off
                call "%s"
                """.formatted(wrapper.toAbsolutePath().normalize());
        Files.writeString(launcher, content);
        return launcher;
    }

    private boolean unregisterWindowsStartupFolder(String safeName) throws IOException {
        String appData = System.getenv("APPDATA");
        Path startupDir = appData == null || appData.isBlank()
                ? Paths.get(System.getProperty("user.home"), "AppData", "Roaming", "Microsoft", "Windows", "Start Menu", "Programs", "Startup")
                : Paths.get(appData, "Microsoft", "Windows", "Start Menu", "Programs", "Startup");
        Path launcher = startupDir.resolve("MABO_" + safeName + ".cmd");
        Path wrapper = resolveMaboRoot().resolve("startup").resolve("MABO_" + safeName + ".cmd");
        boolean launcherDeleted = Files.deleteIfExists(launcher);
        boolean wrapperDeleted = Files.deleteIfExists(wrapper);
        return launcherDeleted || wrapperDeleted;
    }

    private boolean registerLinux(String taskName, Path scriptPath) throws IOException {
        Path autostart = Path.of(System.getProperty("user.home"), ".config", "autostart");
        Files.createDirectories(autostart);
        String safeName = safeTaskName(taskName);
        Path desktopFile = autostart.resolve("mabo-" + safeName + ".desktop");
        String javaExe = Path.of(System.getProperty("java.home"), "bin", "java").toString();
        String classPath = System.getProperty("java.class.path");
        String content = """
                [Desktop Entry]
                Type=Application
                Name=MABO %s
                Exec=%s -cp %s Main %s
                X-GNOME-Autostart-enabled=true
                """.formatted(safeName, javaExe, classPath, scriptPath.toAbsolutePath());
        Files.writeString(desktopFile, content);
        logger.info("Registro Linux startup creado: " + desktopFile);
        return true;
    }

    private String safeTaskName(String taskName) {
        String value = taskName == null || taskName.isBlank() ? "Tarea" : taskName;
        return value.replaceAll("[^A-Za-z0-9._-]", "_");
    }

    private Path resolveMaboRoot() {
        String localAppData = System.getenv("LOCALAPPDATA");
        return localAppData == null || localAppData.isBlank()
                ? Paths.get(System.getProperty("user.home"), ".mabo")
                : Paths.get(localAppData, "MABO");
    }
}
