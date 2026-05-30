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
        String userHome = System.getProperty("user.home");
        if (userHome != null) {
            addDefaultRoot(Paths.get(userHome));
        }
        addDefaultRoot(Paths.get(System.getProperty("user.dir")));
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

        String s = abs.toString().toLowerCase();
        // bloquear rutas raíz
        if (abs.getParent() == null) {
            throw new SecurityException("Acceso a ruta raíz bloqueado: " + abs);
        }
        // bloquear carpetas críticas por nombre
        if (s.contains("windows") || s.contains("program files") || s.contains("system32")) {
            throw new SecurityException("Acceso a carpeta crítica bloqueado: " + abs);
        }

        // allow if path is under any allowed root
        for (Path root : allowedRoots) {
            if (abs.startsWith(root)) {
                return;
            }
        }

        // if not explicitly allowed, deny access
        throw new SecurityException("Acceso denegado fuera de rutas permitidas: " + abs);
    }

    private static void addDefaultRoot(Path p) {
        if (p != null) {
            allowedRoots.add(p.toAbsolutePath().normalize());
        }
    }
}
