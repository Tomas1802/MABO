package shell;

import exceptions.ExecutionException;
import exceptions.SecurityException;
import logs.AuditService;
import logs.LoggerService;
import model.ShellResult;
import security.SecurityValidator;
import utils.ConfirmationService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ShellCommandService {
    private final LoggerService logger;
    private final AuditService audit = new AuditService();

    public ShellCommandService(LoggerService logger) {
        this.logger = logger;
    }

    public ShellResult execute(String shell, String command, boolean simulate) {
        String normalizedShell = shell == null ? "" : shell.trim().toLowerCase(Locale.ROOT);
        String normalizedCommand = command == null ? "" : command.trim();
        if (normalizedCommand.isEmpty()) {
            throw new ExecutionException("El comando nativo no puede estar vacío");
        }
        ensureKnownShell(normalizedShell);
        validateCommand(normalizedCommand);

        Path workingDir = defaultWorkingDirectory();
        SecurityValidator.validatePath(workingDir);

        if (simulate) {
            audit.record("SHELL_" + normalizedShell.toUpperCase(Locale.ROOT), workingDir, null, true,
                    "simulated: " + normalizedCommand);
            logger.info("Simulación comando " + normalizedShell + ": " + normalizedCommand);
            return new ShellResult(normalizedShell, normalizedCommand, 0, "", "", true);
        }

        ensureSupportedShell(normalizedShell);

        if (isRisky(normalizedCommand)) {
            boolean ok = ConfirmationService.confirm("Ejecutar comando " + shell + " potencialmente riesgoso?", false);
            if (!ok) {
                audit.record("SHELL_" + normalizedShell.toUpperCase(Locale.ROOT), workingDir, null, false,
                        "cancelled by user");
                return new ShellResult(normalizedShell, normalizedCommand, 130, "", "cancelled by user", false);
            }
        }

        List<String> commandLine = switch (normalizedShell) {
            case "powershell" -> List.of("powershell.exe", "-NoProfile", "-ExecutionPolicy", "Bypass", "-Command", normalizedCommand);
            case "linux" -> List.of("/bin/sh", "-lc", normalizedCommand);
            default -> throw new ExecutionException("Shell no soportado: " + shell);
        };

        try {
            Process process = new ProcessBuilder(commandLine)
                    .directory(workingDir.toFile())
                    .redirectErrorStream(false)
                    .start();
            boolean finished = process.waitFor(60, TimeUnit.SECONDS);
            if (!finished) {
                process.destroyForcibly();
                throw new ExecutionException("El comando nativo superó el tiempo máximo de 60 segundos");
            }
            String stdout = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            String stderr = new String(process.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
            ShellResult result = new ShellResult(normalizedShell, normalizedCommand, process.exitValue(), stdout, stderr, false);
            audit.record("SHELL_" + normalizedShell.toUpperCase(Locale.ROOT), workingDir, null,
                    process.exitValue() == 0, "exit=" + process.exitValue());
            logger.info("Comando " + normalizedShell + " finalizado: " + result);
            return result;
        } catch (IOException e) {
            throw new ExecutionException("No se pudo ejecutar el comando nativo: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExecutionException("Ejecución de comando interrumpida", e);
        }
    }

    private Path defaultWorkingDirectory() {
        Path root = Paths.get(System.getProperty("user.home"), "Documents", "DSLDemo").toAbsolutePath().normalize();
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new ExecutionException("No se pudo preparar el directorio seguro de comandos: " + root, e);
        }
        return root;
    }

    private void ensureSupportedShell(String shell) {
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        if ("powershell".equals(shell)) {
            if (!os.contains("win")) {
                throw new ExecutionException("PowerShell solo está habilitado automáticamente en Windows");
            }
            return;
        }
        if ("linux".equals(shell)) {
            if (os.contains("win")) {
                throw new ExecutionException("Comandos Linux solo están habilitados automáticamente en Linux/Unix");
            }
            return;
        }
        throw new ExecutionException("Shell no soportado: " + shell);
    }

    private void ensureKnownShell(String shell) {
        if (!"powershell".equals(shell) && !"linux".equals(shell)) {
            throw new ExecutionException("Shell no soportado: " + shell);
        }
    }

    private void validateCommand(String command) {
        String lower = command.toLowerCase(Locale.ROOT);
        String[] blocked = {
                "format ", "shutdown", "restart-computer", "stop-computer", "mkfs", ":(){",
                "rm -rf /", "remove-item -recurse -force c:\\", "del /f /s /q c:\\",
                "rd /s /q c:\\", "diskpart", "bcdedit", "reg delete"
        };
        for (String token : blocked) {
            if (lower.contains(token)) {
                throw new SecurityException("Comando nativo bloqueado por seguridad: " + token.trim());
            }
        }
    }

    private boolean isRisky(String command) {
        String lower = command.toLowerCase(Locale.ROOT);
        String[] risky = {
                "remove-item", "del ", "erase ", "rmdir", "rd ", "rm ", "mv ", "move-item",
                "cp ", "copy-item", "set-content", "new-item", "chmod", "chown", "mkdir", "touch"
        };
        for (String token : risky) {
            if (lower.contains(token)) return true;
        }
        return false;
    }
}
