package security;

import exceptions.SecurityException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SecurityValidator {
    private static final Set<Path> allowedRoots = Collections.synchronizedSet(new HashSet<>());

    static {
        // default allowed root: %USERPROFILE%/Documents/DSLDemo (expand user.home)
        String userHome = System.getProperty("user.home");
        if (userHome != null) {
            Path defaultRoot = Paths.get(userHome).resolve("Documents").resolve("DSLDemo").toAbsolutePath().normalize();
            allowedRoots.add(defaultRoot);
        }
    }

    public static void addAllowedRoot(Path p) {
        Objects.requireNonNull(p, "allowed root cannot be null");
        allowedRoots.add(p.toAbsolutePath().normalize());
    }

    public static Set<Path> getAllowedRoots() {
        return Collections.unmodifiableSet(allowedRoots);
    }

    public static void validatePath(Path p) {
        if (p == null) throw new SecurityException("Ruta inválida: null");
        Path abs = p.toAbsolutePath().normalize();

        // allow if path is under any allowed root
        for (Path root : allowedRoots) {
            if (abs.startsWith(root)) {
                return;
            }
        }

        String s = abs.toString().toLowerCase();
        // bloquear rutas raíz
        if (abs.getParent() == null) {
            throw new SecurityException("Acceso a ruta raíz bloqueado: " + abs);
        }
        // bloquear carpetas críticas por nombre
        if (s.contains("windows") || s.contains("program files") || s.contains("system32")) {
            throw new SecurityException("Acceso a carpeta crítica bloqueado: " + abs);
        }

        // if not explicitly allowed, deny access
        throw new SecurityException("Acceso denegado fuera de rutas permitidas: " + abs);
    }
}
