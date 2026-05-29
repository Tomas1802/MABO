package scheduler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleStore {
    private final Path storeFile;

    public ScheduleStore() {
        Path root = Paths.get(System.getProperty("user.home"), "Documents", "DSLDemo");
        this.storeFile = root.resolve("schedules.ndjson").toAbsolutePath().normalize();
        try { if (storeFile.getParent() != null) Files.createDirectories(storeFile.getParent()); } catch (IOException ignored) {}
    }

    public void saveSchedule(String taskName, long periodSeconds) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("ts", Instant.now().toString());
        entry.put("task", taskName);
        entry.put("period", periodSeconds);
        try {
            String line = toJson(entry) + System.lineSeparator();
            Files.writeString(storeFile, line, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("No se pudo guardar schedule: " + e.getMessage());
        }
    }

    public List<ScheduleEntry> loadSchedules() {
        List<ScheduleEntry> out = new ArrayList<>();
        if (!Files.exists(storeFile)) return out;
        try {
            List<String> lines = Files.readAllLines(storeFile);
            for (String l : lines) {
                String task = extract(l, '"'+"task"+'"');
                String period = extract(l, '"'+"period"+'"');
                if (task != null && period != null) {
                    try { out.add(new ScheduleEntry(task, Long.parseLong(period))); } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException ignored) {}
        return out;
    }

    private String extract(String jsonLine, String keyQuoted) {
        int idx = jsonLine.indexOf(keyQuoted);
        if (idx < 0) return null;
        int colon = jsonLine.indexOf(':', idx);
        if (colon < 0) return null;
        int start = colon + 1;
        // skip spaces
        while (start < jsonLine.length() && Character.isWhitespace(jsonLine.charAt(start))) start++;
        if (start >= jsonLine.length()) return null;
        if (jsonLine.charAt(start) == '"') {
            int end = jsonLine.indexOf('"', start+1);
            if (end > start) return jsonLine.substring(start+1, end);
            return null;
        } else {
            // numeric
            int end = start;
            while (end < jsonLine.length() && (Character.isDigit(jsonLine.charAt(end)) || jsonLine.charAt(end)=='-')) end++;
            return jsonLine.substring(start, end);
        }
    }

    private String toJson(Map<String, Object> m) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        boolean first = true;
        for (Map.Entry<String, Object> e : m.entrySet()) {
            if (!first) sb.append(','); first = false;
            sb.append('"').append(e.getKey()).append('"').append(':');
            Object v = e.getValue();
            if (v instanceof Number) sb.append(v.toString()); else sb.append('"').append(String.valueOf(v).replace("\"","\\\"")).append('"');
        }
        sb.append('}');
        return sb.toString();
    }

    public static class ScheduleEntry {
        public final String task;
        public final long periodSeconds;
        public ScheduleEntry(String task, long periodSeconds) { this.task = task; this.periodSeconds = periodSeconds; }
    }
}
