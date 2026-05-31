package scheduler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScheduleStore {
    private final Path storeFile;

    public ScheduleStore() {
        String configured = System.getProperty("mabo.schedule.file");
        if (configured != null && !configured.isBlank()) {
            this.storeFile = Paths.get(configured).toAbsolutePath().normalize();
        } else {
            Path root = Paths.get(System.getProperty("user.home"), "Documents", "DSLDemo");
            this.storeFile = root.resolve("schedules.ndjson").toAbsolutePath().normalize();
        }
        try {
            if (storeFile.getParent() != null) {
                Files.createDirectories(storeFile.getParent());
            }
        } catch (IOException ignored) {
        }
    }

    public Path getStoreFile() {
        return storeFile;
    }

    public void saveIntervalSchedule(String taskName, long periodSeconds) {
        ScheduleEntry entry = new ScheduleEntry(taskName, "interval", periodSeconds, null, null, Instant.now().toString());
        saveOrReplace(entry);
    }

    public void saveOneTimeSchedule(String taskName, LocalDateTime fireAt) {
        ScheduleEntry entry = new ScheduleEntry(taskName, "once", 0L, null, fireAt.toString(), Instant.now().toString());
        saveOrReplace(entry);
    }

    public void saveStartupSchedule(String taskName) {
        ScheduleEntry entry = new ScheduleEntry(taskName, "startup", 0L, null, null, Instant.now().toString());
        saveOrReplace(entry);
    }

    public boolean deleteSchedule(String taskName) {
        List<ScheduleEntry> schedules = loadSchedules();
        boolean removed = schedules.removeIf(entry -> entry.task.equals(taskName));
        if (removed) {
            writeAll(schedules);
        }
        return removed;
    }

    public int deleteAllSchedules() {
        List<ScheduleEntry> schedules = loadSchedules();
        int removed = schedules.size();
        if (removed > 0) {
            writeAll(new ArrayList<>());
        }
        return removed;
    }

    public List<ScheduleEntry> loadSchedules() {
        Map<String, ScheduleEntry> byTask = new LinkedHashMap<>();
        if (!Files.exists(storeFile)) {
            return new ArrayList<>();
        }
        try {
            List<String> lines = Files.readAllLines(storeFile);
            for (String line : lines) {
                ScheduleEntry entry = parseEntry(line);
                if (entry != null) {
                    byTask.put(entry.task, entry);
                }
            }
        } catch (IOException ignored) {
        }
        return new ArrayList<>(byTask.values());
    }

    private ScheduleEntry parseEntry(String line) {
        String task = extract(line, "\"task\"");
        if (task == null || task.isBlank()) {
            return null;
        }
        String kind = extract(line, "\"kind\"");
        String period = extract(line, "\"period\"");
        String time = extract(line, "\"time\"");
        String fireAt = extract(line, "\"fireAt\"");
        String createdAt = extract(line, "\"createdAt\"");

        long periodSeconds = 0L;
        if (period != null && !period.isBlank()) {
            try {
                periodSeconds = Long.parseLong(period);
            } catch (NumberFormatException ignored) {
            }
        }

        if ("daily".equalsIgnoreCase(kind)) {
            kind = "once";
            if (fireAt == null && time != null) {
                fireAt = time;
            }
        }

        return new ScheduleEntry(task, kind == null ? "interval" : kind, periodSeconds, time, fireAt, createdAt);
    }

    private void saveOrReplace(ScheduleEntry entry) {
        List<ScheduleEntry> schedules = loadSchedules();
        schedules.removeIf(existing -> existing.task.equals(entry.task));
        schedules.add(entry);
        writeAll(schedules);
    }

    private void writeAll(List<ScheduleEntry> schedules) {
        try {
            if (storeFile.getParent() != null) {
                Files.createDirectories(storeFile.getParent());
            }
            StringBuilder out = new StringBuilder();
            for (ScheduleEntry entry : schedules) {
                out.append(toJson(entry)).append(System.lineSeparator());
            }
            Files.writeString(storeFile, out.toString(),
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("No se pudo guardar schedule: " + e.getMessage());
        }
    }

    private String extract(String jsonLine, String keyQuoted) {
        int idx = jsonLine.indexOf(keyQuoted);
        if (idx < 0) return null;
        int colon = jsonLine.indexOf(':', idx);
        if (colon < 0) return null;
        int start = colon + 1;
        while (start < jsonLine.length() && Character.isWhitespace(jsonLine.charAt(start))) start++;
        if (start >= jsonLine.length()) return null;
        if (jsonLine.charAt(start) == '"') {
            int end = start + 1;
            StringBuilder value = new StringBuilder();
            while (end < jsonLine.length()) {
                char current = jsonLine.charAt(end);
                if (current == '\\' && end + 1 < jsonLine.length()) {
                    value.append(jsonLine.charAt(end + 1));
                    end += 2;
                    continue;
                }
                if (current == '"') {
                    return value.toString();
                }
                value.append(current);
                end++;
            }
            return null;
        }
        int end = start;
        while (end < jsonLine.length() && (Character.isDigit(jsonLine.charAt(end)) || jsonLine.charAt(end) == '-')) {
            end++;
        }
        return jsonLine.substring(start, end);
    }

    private String toJson(ScheduleEntry entry) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("createdAt", entry.createdAt != null ? entry.createdAt : Instant.now().toString());
        fields.put("kind", entry.kind);
        fields.put("task", entry.task);
        if (entry.periodSeconds > 0) fields.put("period", entry.periodSeconds);
        if (entry.time != null) fields.put("time", entry.time);
        if (entry.fireAt != null) fields.put("fireAt", entry.fireAt);

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        boolean first = true;
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            if (!first) sb.append(',');
            first = false;
            sb.append('"').append(escape(field.getKey())).append('"').append(':');
            Object value = field.getValue();
            if (value instanceof Number) {
                sb.append(value);
            } else {
                sb.append('"').append(escape(String.valueOf(value))).append('"');
            }
        }
        sb.append('}');
        return sb.toString();
    }

    private String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static class ScheduleEntry {
        public final String task;
        public final String kind;
        public final long periodSeconds;
        public final String time;
        public final String fireAt;
        public final String createdAt;

        public ScheduleEntry(String task, String kind, long periodSeconds, String time, String fireAt, String createdAt) {
            this.task = task;
            this.kind = kind;
            this.periodSeconds = periodSeconds;
            this.time = time;
            this.fireAt = fireAt;
            this.createdAt = createdAt;
        }
    }
}
