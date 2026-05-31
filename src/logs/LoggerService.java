package logs;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerService {
    private final Path logFile;
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LoggerService(String fileName) {
        Path configured = Paths.get(fileName);
        if (configured.isAbsolute()) {
            this.logFile = configured;
        } else {
            Path evidenceRoot = Paths.get(System.getProperty("user.home"), "Documents", "MABO");
            this.logFile = evidenceRoot.resolve(configured).normalize();
        }
        try {
            if (logFile.getParent() != null) {
                Files.createDirectories(logFile.getParent());
            }
        } catch (IOException ignored) {
        }
    }

    public Path getLogFile() {
        return logFile;
    }

    public void info(String msg) { write("INFO", msg); }
    public void error(String msg) { write("ERROR", msg); }
    public void error(String msg, Throwable t) { write("ERROR", msg + "\n" + stackTrace(t)); }

    private void write(String level, String msg) {
        String line = String.format("%s [%s] %s\n", df.format(LocalDateTime.now()), level, msg);
        try {
            Files.write(logFile, line.getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("No se pudo escribir log: " + e.getMessage());
        }
    }

    private String stackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
