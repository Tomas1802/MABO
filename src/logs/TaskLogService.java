package logs;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskLogService {
    private static final DateTimeFormatter FILE_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter LINE_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Path logDirectory;

    public TaskLogService() {
        this(resolveDefaultLogDirectory());
    }

    public TaskLogService(Path logDirectory) {
        this.logDirectory = logDirectory.toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.logDirectory);
        } catch (IOException ignored) {
        }
    }

    public Path getLogDirectory() {
        return logDirectory;
    }

    public Path taskLogFile(String taskName) {
        String date = FILE_DATE.format(LocalDateTime.now());
        return logDirectory.resolve(safeFileName(taskName) + "-" + date + ".log");
    }

    public void scheduler(String message) {
        write(logDirectory.resolve("scheduler.log"), "SCHEDULER", message);
    }

    public void start(String taskName, Path workingDirectory) {
        write(taskLogFile(taskName), "START", "Tarea " + taskName + " iniciada en " + workingDirectory);
    }

    public void event(String taskName, String message) {
        write(taskLogFile(taskName), "INFO", message);
    }

    public void end(String taskName, long durationMillis) {
        write(taskLogFile(taskName), "END", "Tarea " + taskName + " finalizada en " + durationMillis + " ms");
    }

    public void error(String taskName, Throwable error) {
        write(taskLogFile(taskName), "ERROR", error.getMessage() + System.lineSeparator() + stackTrace(error));
    }

    private void write(Path file, String level, String message) {
        String line = LINE_DATE.format(LocalDateTime.now()) + " [" + level + "] " + message + System.lineSeparator();
        try {
            if (file.getParent() != null) {
                Files.createDirectories(file.getParent());
            }
            Files.writeString(file, line, StandardCharsets.UTF_8,
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.err.println("No se pudo escribir log de tarea: " + ex.getMessage());
        }
    }

    private String stackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    private String safeFileName(String taskName) {
        String value = taskName == null || taskName.isBlank() ? "tarea" : taskName;
        return value.replaceAll("[^A-Za-z0-9._-]", "_");
    }

    private static Path resolveDefaultLogDirectory() {
        String configured = System.getProperty("mabo.log.dir");
        if (configured != null && !configured.isBlank()) {
            return Paths.get(configured);
        }

        String localAppData = System.getenv("LOCALAPPDATA");
        if (localAppData != null && !localAppData.isBlank()) {
            return Paths.get(localAppData, "MABO", "logs", "tasks");
        }

        return Paths.get(System.getProperty("user.home"), ".mabo", "logs", "tasks");
    }
}
