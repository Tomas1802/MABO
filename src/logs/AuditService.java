package logs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class AuditService {
    private final Path auditFile;

    public AuditService() {
        Path configured = Paths.get("audit.log");
        Path evidenceRoot = Paths.get(System.getProperty("user.home"), "Documents", "DSLDemo");
        this.auditFile = evidenceRoot.resolve(configured).normalize();
        try {
            if (auditFile.getParent() != null) Files.createDirectories(auditFile.getParent());
        } catch (IOException ignored) {}
    }

    public void record(String action, Path before, Path after, boolean success, String message) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("ts", Instant.now().toString());
        entry.put("action", action);
        entry.put("from", before != null ? before.toAbsolutePath().toString() : null);
        entry.put("to", after != null ? after.toAbsolutePath().toString() : null);
        entry.put("success", success);
        entry.put("message", message != null ? message : "");
        try {
            String json = toJson(entry) + System.lineSeparator();
            Files.writeString(auditFile, json, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("No se pudo escribir audit log: " + e.getMessage());
        }
    }

    private String toJson(Map<String, Object> m) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        boolean first = true;
        for (Map.Entry<String, Object> e : m.entrySet()) {
            if (!first) sb.append(',');
            first = false;
            sb.append('"').append(escape(e.getKey())).append('"').append(':');
            Object v = e.getValue();
            if (v == null) {
                sb.append("null");
            } else if (v instanceof Boolean) {
                sb.append(v.toString());
            } else {
                sb.append('"').append(escape(v.toString())).append('"');
            }
        }
        sb.append('}');
        return sb.toString();
    }

    private String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }
}
