package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

public class FilterUtils {
    public static Predicate<Path> byExtension(String ext) {
        String e = ext.startsWith(".") ? ext.toLowerCase() : ("." + ext).toLowerCase();
        return p -> p.getFileName() != null && p.getFileName().toString().toLowerCase().endsWith(e);
    }

    public static Predicate<Path> olderThanDays(long days) {
        return p -> {
            try {
                BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);
                Instant modified = attrs.lastModifiedTime().toInstant();
                return modified.isBefore(Instant.now().minus(days, ChronoUnit.DAYS));
            } catch (Exception ex) { return false; }
        };
    }

    public static Predicate<Path> nameContains(String token) {
        String t = token.toLowerCase();
        return p -> p.getFileName() != null && p.getFileName().toString().toLowerCase().contains(t);
    }

    public static Predicate<Path> sizeComparator(long sizeMb, String comparator) {
        long bytes = sizeMb * 1024 * 1024;
        switch (comparator) {
            case ">": return p -> fileSize(p) > bytes;
            case "<": return p -> fileSize(p) < bytes;
            case ">=": return p -> fileSize(p) >= bytes;
            case "<=": return p -> fileSize(p) <= bytes;
            case "==": return p -> fileSize(p) == bytes;
            case "!=": return p -> fileSize(p) != bytes;
            default: return p -> false;
        }
    }

    public static Predicate<Path> byPrefix(String prefix) {
        String pfx = prefix.toLowerCase();
        return p -> p.getFileName() != null && p.getFileName().toString().toLowerCase().startsWith(pfx);
    }

    public static Predicate<Path> bySuffix(String suffix) {
        String sfx = suffix.toLowerCase();
        return p -> {
            if (p.getFileName() == null) return false;
            String name = p.getFileName().toString().toLowerCase();
            int dot = name.lastIndexOf('.');
            String base = dot >= 0 ? name.substring(0, dot) : name;
            return base.endsWith(sfx);
        };
    }

    public static Predicate<Path> recentThanDays(long days) {
        return p -> {
            try {
                BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);
                Instant modified = attrs.lastModifiedTime().toInstant();
                return modified.isAfter(Instant.now().minus(days, ChronoUnit.DAYS));
            } catch (Exception ex) { return false; }
        };
    }

    private static long fileSize(Path p) {
        try { return Files.size(p); } catch (Exception e) { return -1; }
    }
}
