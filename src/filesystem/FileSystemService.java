package filesystem;

import exceptions.ExecutionException;
import security.SecurityValidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import logs.AuditService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.ConfirmationService;

public class FileSystemService {

    private static final Pattern WINDOWS_ENV_PATTERN = Pattern.compile("%([A-Za-z_][A-Za-z0-9_]*)%");
    private static final Pattern UNIX_ENV_PATTERN = Pattern.compile("\\$\\{([A-Za-z_][A-Za-z0-9_]*)\\}|\\$([A-Za-z_][A-Za-z0-9_]*)");
    private Path workingDirectory = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();

    public Path pathOf(Object value) {
        if (value == null) {
            throw new ExecutionException("La ruta no puede ser nula");
        }
        if (value instanceof Path path) {
            return resolve(path);
        }
        String raw = expandPathTokens(value.toString().trim());
        Path path = Paths.get(raw);
        return resolve(path);
    }

    private Path resolve(Path path) {
        Path resolved = path.isAbsolute() ? path : workingDirectory.resolve(path);
        return resolved.toAbsolutePath().normalize();
    }

    public Path getWorkingDirectory() {
        return workingDirectory;
    }

    public Path changeWorkingDirectory(Object value) {
        Path target = pathOf(value);
        SecurityValidator.validatePath(target);
        if (!Files.isDirectory(target)) {
            throw new ExecutionException("La ruta no es una carpeta existente: " + target);
        }
        workingDirectory = target;
        return workingDirectory;
    }

    // For display: expands env vars; normalizes separators only if result is a Windows absolute path
    public String expandTokens(String raw) {
        String result = expandEnvVars(raw);
        if (result.length() >= 2 && Character.isLetter(result.charAt(0)) && result.charAt(1) == ':') {
            result = result.replace('/', java.io.File.separatorChar);
        }
        return result;
    }

    // For path resolution: expands env vars AND normalizes separators
    private String expandPathTokens(String raw) {
        String expanded = expandEnvVars(raw).replace('/', java.io.File.separatorChar);
        if (isWindows() && startsWithSingleRootSeparator(expanded)) {
            return expanded.replaceFirst("^[\\\\/]+", "");
        }
        return expanded;
    }

    private boolean isWindows() {
        return java.io.File.separatorChar == '\\';
    }

    private boolean startsWithSingleRootSeparator(String path) {
        return path.length() > 1
                && (path.charAt(0) == '\\' || path.charAt(0) == '/')
                && path.charAt(1) != '\\'
                && path.charAt(1) != '/';
    }

    private String expandEnvVars(String raw) {
        String expanded = raw;
        if (expanded.startsWith("~")) {
            expanded = System.getProperty("user.home") + expanded.substring(1);
        }

        Matcher windows = WINDOWS_ENV_PATTERN.matcher(expanded);
        StringBuffer windowsBuffer = new StringBuffer();
        while (windows.find()) {
            String value = System.getenv(windows.group(1));
            windows.appendReplacement(windowsBuffer, Matcher.quoteReplacement(value != null ? value : windows.group(0)));
        }
        windows.appendTail(windowsBuffer);

        Matcher unix = UNIX_ENV_PATTERN.matcher(windowsBuffer.toString());
        StringBuffer unixBuffer = new StringBuffer();
        while (unix.find()) {
            String envName = unix.group(1) != null ? unix.group(1) : unix.group(2);
            String value = System.getenv(envName);
            unix.appendReplacement(unixBuffer, Matcher.quoteReplacement(value != null ? value : unix.group(0)));
        }
        unix.appendTail(unixBuffer);

        return unixBuffer.toString();
    }

    public List<Path> findFiles(Path root, Predicate<Path> filter) {
        Path resolvedRoot = resolve(root);
        SecurityValidator.validatePath(resolvedRoot);
        List<Path> found = new ArrayList<>();
        if (!Files.exists(resolvedRoot) || !Files.isDirectory(resolvedRoot)) {
            return found;
        }
        try {
            Files.walkFileTree(resolvedRoot, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    try {
                        SecurityValidator.validatePath(dir);
                        return FileVisitResult.CONTINUE;
                    } catch (RuntimeException ex) {
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        if (filter.test(file)) found.add(file);
                    } catch (RuntimeException ignored) {
                        return FileVisitResult.CONTINUE;
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new ExecutionException("Error buscando archivos", e);
        }
        return found;
    }

    public List<Path> findDirectories(Path root, Predicate<Path> filter) {
        Path resolvedRoot = resolve(root);
        SecurityValidator.validatePath(resolvedRoot);
        List<Path> found = new ArrayList<>();
        if (!Files.exists(resolvedRoot) || !Files.isDirectory(resolvedRoot)) {
            return found;
        }
        try {
            Files.walkFileTree(resolvedRoot, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    try {
                        SecurityValidator.validatePath(dir);
                        if (filter.test(dir)) {
                            found.add(dir);
                        }
                        return FileVisitResult.CONTINUE;
                    } catch (RuntimeException ex) {
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new ExecutionException("Error buscando carpetas", e);
        }
        return found;
    }

    public void createFile(Path file, boolean simulate) {
        Path resolved = resolve(file);
        AuditService audit = new AuditService();
        SecurityValidator.validatePath(resolved);
        try {
            if (simulate) {
                audit.record("CREATE_FILE", null, resolved, true, "simulated");
                return;
            }
            if (resolved.getParent() != null) {
                Files.createDirectories(resolved.getParent());
            }
            if (Files.notExists(resolved)) {
                Files.createFile(resolved);
            }
            audit.record("CREATE_FILE", null, resolved, true, "ok");
        } catch (IOException e) {
            audit.record("CREATE_FILE", null, resolved, false, e.getMessage());
            throw new ExecutionException("Error creando archivo: " + resolved, e);
        }
    }

    public String readText(Path file) {
        Path resolved = resolve(file);
        SecurityValidator.validatePath(resolved);
        try {
            return Files.readString(resolved);
        } catch (IOException e) {
            throw new ExecutionException("Error leyendo archivo: " + resolved, e);
        }
    }

    public void writeText(Path file, String content, boolean append, boolean simulate) {
        Path resolved = resolve(file);
        AuditService audit = new AuditService();
        SecurityValidator.validatePath(resolved);
        try {
            if (simulate) {
                audit.record(append ? "APPEND" : "WRITE", null, resolved, true, "simulated");
                return;
            }
            if (resolved.getParent() != null) {
                Files.createDirectories(resolved.getParent());
            }
            if (append) {
                Files.writeString(resolved, content, StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else {
                Files.writeString(resolved, content, StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
            audit.record(append ? "APPEND" : "WRITE", null, resolved, true, "ok");
        } catch (IOException e) {
            audit.record(append ? "APPEND" : "WRITE", null, resolved, false, e.getMessage());
            throw new ExecutionException("Error escribiendo archivo: " + resolved, e);
        }
    }

    public List<Path> listChildren(Path dir) {
        Path resolved = resolve(dir);
        SecurityValidator.validatePath(resolved);
        try {
            if (!Files.exists(resolved) || !Files.isDirectory(resolved)) {
                return List.of();
            }
            try (java.util.stream.Stream<Path> stream = Files.list(resolved)) {
                return stream.toList();
            }
        } catch (IOException e) {
            throw new ExecutionException("Error listando contenido: " + resolved, e);
        }
    }

    public long sizeOf(Path path) {
        Path resolved = resolve(path);
        SecurityValidator.validatePath(resolved);
        if (!Files.exists(resolved)) {
            return 0L;
        }
        try {
            if (Files.isDirectory(resolved)) {
                final long[] total = {0L};
                Files.walkFileTree(resolved, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        total[0] += attrs.size();
                        return FileVisitResult.CONTINUE;
                    }
                });
                return total[0];
            }
            return Files.size(resolved);
        } catch (IOException e) {
            throw new ExecutionException("Error calculando tamaño: " + resolved, e);
        }
    }

    public long countFiles(Path path) {
        Path resolved = resolve(path);
        SecurityValidator.validatePath(resolved);
        if (!Files.exists(resolved)) {
            return 0L;
        }
        try {
            if (!Files.isDirectory(resolved)) {
                return Files.isRegularFile(resolved) ? 1L : 0L;
            }
            final long[] total = {0L};
            Files.walkFileTree(resolved, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (Files.isRegularFile(file)) {
                        total[0]++;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            return total[0];
        } catch (IOException e) {
            throw new ExecutionException("Error contando archivos: " + resolved, e);
        }
    }

    public Object readProperty(Path path, String property) {
        Path resolved = resolve(path);
        SecurityValidator.validatePath(resolved);
        String key = property == null ? "" : property.trim().toLowerCase();
        try {
            return switch (key) {
                case "nombre" -> resolved.getFileName() != null ? resolved.getFileName().toString() : resolved.toString();
                case "ruta" -> resolved.toString();
                case "extension" -> {
                    String name = resolved.getFileName() != null ? resolved.getFileName().toString() : resolved.toString();
                    int dot = name.lastIndexOf('.');
                    yield dot >= 0 ? name.substring(dot + 1) : "";
                }
                case "tamano" -> sizeOf(resolved);
                case "existe" -> Files.exists(resolved);
                case "escarpeta" -> Files.isDirectory(resolved);
                case "esarchivo" -> Files.isRegularFile(resolved);
                default -> throw new ExecutionException("Propiedad no soportada: " + property);
            };
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void move(Path source, Path target, boolean simulate) {
        Path resolvedSource = resolve(source);
        Path resolvedTarget = resolve(target);
        AuditService audit = new logs.AuditService();
        SecurityValidator.validatePath(resolvedSource);
        SecurityValidator.validatePath(resolvedTarget);
        if (!Files.exists(resolvedSource)) {
            audit.record("MOVE", resolvedSource, resolvedTarget, false, "source not found");
            throw new ExecutionException("Archivo origen no encontrado: " + resolvedSource);
        }
        if (!Files.isReadable(resolvedSource)) {
            audit.record("MOVE", resolvedSource, resolvedTarget, false, "source not readable");
            throw new ExecutionException("Archivo origen no legible: " + resolvedSource);
        }
        if (!simulate && Files.exists(resolvedTarget)) {
            boolean ok = ConfirmationService.confirm("El destino existe: " + resolvedTarget + ". Sobrescribir?", false);
            if (!ok) {
                audit.record("MOVE", resolvedSource, resolvedTarget, false, "cancelled by user");
                return;
            }
        }
        try {
            if (simulate) {
                audit.record("MOVE", resolvedSource, resolvedTarget, true, "simulated");
                return;
            }
            if (resolvedTarget.getParent() != null) {
                Files.createDirectories(resolvedTarget.getParent());
            }
            Files.move(resolvedSource, resolvedTarget, StandardCopyOption.REPLACE_EXISTING);
            audit.record("MOVE", resolvedSource, resolvedTarget, true, "ok");
        } catch (IOException e) {
            audit.record("MOVE", resolvedSource, resolvedTarget, false, e.getMessage());
            throw new ExecutionException("Error moviendo archivo: " + resolvedSource + " -> " + resolvedTarget, e);
        }
    }

    public void copy(Path source, Path target, boolean simulate) {
        Path resolvedSource = resolve(source);
        Path resolvedTarget = resolve(target);
        AuditService audit = new logs.AuditService();
        SecurityValidator.validatePath(resolvedSource);
        SecurityValidator.validatePath(resolvedTarget);
        if (!Files.exists(resolvedSource)) {
            audit.record("COPY", resolvedSource, resolvedTarget, false, "source not found");
            throw new ExecutionException("Archivo origen no encontrado: " + resolvedSource);
        }
        if (Files.isDirectory(resolvedSource) && isSameOrInside(resolvedSource, resolvedTarget)) {
            audit.record("COPY", resolvedSource, resolvedTarget, false, "target inside source");
            throw new ExecutionException("El destino no puede estar dentro de la carpeta origen: " + resolvedTarget);
        }
        try {
            if (simulate) {
                audit.record("COPY", resolvedSource, resolvedTarget, true, "simulated");
                return;
            }
            if (resolvedTarget.getParent() != null) {
                Files.createDirectories(resolvedTarget.getParent());
            }
            if (Files.isDirectory(resolvedSource)) {
                copyDirectory(resolvedSource, resolvedTarget);
            } else {
                Files.copy(resolvedSource, resolvedTarget, StandardCopyOption.REPLACE_EXISTING);
            }
            audit.record("COPY", resolvedSource, resolvedTarget, true, "ok");
        } catch (IOException e) {
            audit.record("COPY", resolvedSource, resolvedTarget, false, e.getMessage());
            throw new ExecutionException("Error copiando archivo: " + resolvedSource + " -> " + resolvedTarget, e);
        }
    }

    public void delete(Path p, boolean simulate) {
        delete(p, simulate, false);
    }

    public void delete(Path p, boolean simulate, boolean skipConfirmation) {
        Path resolved = resolve(p);
        AuditService audit = new logs.AuditService();
        SecurityValidator.validatePath(resolved);
        if (!Files.exists(resolved)) {
            audit.record("DELETE", resolved, null, false, "not found");
            return; // nothing to delete
        }
        try {
            if (simulate) {
                audit.record("DELETE", resolved, null, true, "simulated");
                return;
            }
            if (!skipConfirmation) {
                boolean ok = ConfirmationService.confirm("Eliminar " + resolved + " ?", false);
                if (!ok) { audit.record("DELETE", resolved, null, false, "cancelled by user"); return; }
            }
            if (Files.isDirectory(resolved)) {
                deleteRecursive(resolved);
            } else {
                Files.deleteIfExists(resolved);
            }
            audit.record("DELETE", resolved, null, true, "ok");
        } catch (IOException e) {
            audit.record("DELETE", resolved, null, false, e.getMessage());
            throw new ExecutionException("Error eliminando archivo: " + resolved, e);
        }
    }

    public void createDirectories(Path dir, boolean simulate) {
        Path resolved = resolve(dir);
        AuditService audit = new logs.AuditService();
        SecurityValidator.validatePath(resolved);
        try {
            if (simulate) {
                audit.record("MKDIR", null, resolved, true, "simulated");
                return;
            }
            Files.createDirectories(resolved);
            audit.record("MKDIR", null, resolved, true, "ok");
        } catch (IOException e) {
            audit.record("MKDIR", null, resolved, false, e.getMessage());
            throw new ExecutionException("Error creando carpeta: " + resolved, e);
        }
    }

    private void copyDirectory(Path source, Path target) throws IOException {
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path relative = source.relativize(dir);
                Path destination = target.resolve(relative);
                if (Files.notExists(destination)) {
                    Files.createDirectories(destination);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path relative = source.relativize(file);
                Files.copy(file, target.resolve(relative), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private boolean isSameOrInside(Path source, Path target) {
        Path normalizedSource = source.toAbsolutePath().normalize();
        Path normalizedTarget = target.toAbsolutePath().normalize();
        return normalizedTarget.equals(normalizedSource) || normalizedTarget.startsWith(normalizedSource);
    }

    private void deleteRecursive(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.deleteIfExists(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.deleteIfExists(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public void compress(Path source, Path target, boolean simulate) {
        Path resolvedSource = resolve(source);
        Path resolvedTarget = resolve(target);
        AuditService audit = new logs.AuditService();
        SecurityValidator.validatePath(resolvedSource);
        SecurityValidator.validatePath(resolvedTarget);
        if (!Files.exists(resolvedSource)) {
            audit.record("COMPRESS", resolvedSource, resolvedTarget, false, "source not found");
            throw new ExecutionException("Origen no encontrado: " + resolvedSource);
        }
        if (simulate) {
            audit.record("COMPRESS", resolvedSource, resolvedTarget, true, "simulated");
            return;
        }
        Path zipTarget = resolvedTarget;
        if (!resolvedTarget.toString().endsWith(".zip")) {
            zipTarget = Paths.get(resolvedTarget + ".zip");
        }
        try {
            if (zipTarget.getParent() != null) Files.createDirectories(zipTarget.getParent());
            try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipTarget))) {
                if (Files.isDirectory(resolvedSource)) {
                    final Path base = resolvedSource;
                    Files.walkFileTree(resolvedSource, new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            String entry = base.relativize(file).toString().replace('\\', '/');
                            zos.putNextEntry(new ZipEntry(entry));
                            Files.copy(file, zos);
                            zos.closeEntry();
                            return FileVisitResult.CONTINUE;
                        }
                    });
                } else {
                    zos.putNextEntry(new ZipEntry(resolvedSource.getFileName().toString()));
                    Files.copy(resolvedSource, zos);
                    zos.closeEntry();
                }
            }
            audit.record("COMPRESS", resolvedSource, zipTarget, true, "ok");
        } catch (IOException e) {
            audit.record("COMPRESS", resolvedSource, zipTarget, false, e.getMessage());
            throw new ExecutionException("Error comprimiendo: " + resolvedSource, e);
        }
    }

    public void decompress(Path source, Path target, boolean simulate) {
        Path resolvedSource = resolve(source);
        Path resolvedTarget = resolve(target);
        AuditService audit = new logs.AuditService();
        SecurityValidator.validatePath(resolvedSource);
        SecurityValidator.validatePath(resolvedTarget);
        if (!Files.exists(resolvedSource)) {
            audit.record("DECOMPRESS", resolvedSource, resolvedTarget, false, "source not found");
            throw new ExecutionException("ZIP no encontrado: " + resolvedSource);
        }
        if (simulate) {
            audit.record("DECOMPRESS", resolvedSource, resolvedTarget, true, "simulated");
            return;
        }
        try {
            Files.createDirectories(resolvedTarget);
            try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(resolvedSource))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    Path entryPath = resolvedTarget.resolve(entry.getName()).normalize();
                    if (!entryPath.startsWith(resolvedTarget)) {
                        throw new ExecutionException("Entrada ZIP fuera del destino (zip slip): " + entry.getName());
                    }
                    if (entry.isDirectory()) {
                        Files.createDirectories(entryPath);
                    } else {
                        if (entryPath.getParent() != null) Files.createDirectories(entryPath.getParent());
                        Files.copy(zis, entryPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                    zis.closeEntry();
                }
            }
            audit.record("DECOMPRESS", resolvedSource, resolvedTarget, true, "ok");
        } catch (IOException e) {
            audit.record("DECOMPRESS", resolvedSource, resolvedTarget, false, e.getMessage());
            throw new ExecutionException("Error descomprimiendo: " + resolvedSource, e);
        }
    }

    public void changePermissions(Path path, String permissions, boolean simulate) {
        Path resolved = resolve(path);
        AuditService audit = new logs.AuditService();
        SecurityValidator.validatePath(resolved);
        if (!Files.exists(resolved)) {
            audit.record("CHMOD", resolved, null, false, "not found");
            throw new ExecutionException("Ruta no encontrada para permisos: " + resolved);
        }
        Set<PosixFilePermission> parsed = parsePermissions(permissions);
        try {
            if (simulate) {
                audit.record("CHMOD", resolved, null, true, "simulated: " + permissions);
                return;
            }
            Files.setPosixFilePermissions(resolved, parsed);
            audit.record("CHMOD", resolved, null, true, "ok: " + permissions);
        } catch (UnsupportedOperationException ex) {
            audit.record("CHMOD", resolved, null, false, "POSIX permissions unsupported");
            throw new ExecutionException("Cambiar Permisos solo está disponible en sistemas con permisos POSIX", ex);
        } catch (IOException ex) {
            audit.record("CHMOD", resolved, null, false, ex.getMessage());
            throw new ExecutionException("Error cambiando permisos: " + resolved, ex);
        }
    }

    private Set<PosixFilePermission> parsePermissions(String raw) {
        String value = raw == null ? "" : raw.trim();
        if (value.matches("[0-7]{3}")) {
            Set<PosixFilePermission> out = new HashSet<>();
            addOctalPermissions(out, value.charAt(0), PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE);
            addOctalPermissions(out, value.charAt(1), PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_EXECUTE);
            addOctalPermissions(out, value.charAt(2), PosixFilePermission.OTHERS_READ, PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE);
            return out;
        }
        if (value.matches("[r-][w-][x-][r-][w-][x-][r-][w-][x-]")) {
            return PosixFilePermissions.fromString(value);
        }
        throw new ExecutionException("Formato de permisos inválido. Usa 755 o rwxr-xr-x");
    }

    private void addOctalPermissions(Set<PosixFilePermission> out, char digit,
                                     PosixFilePermission read,
                                     PosixFilePermission write,
                                     PosixFilePermission execute) {
        int value = digit - '0';
        if ((value & 4) != 0) out.add(read);
        if ((value & 2) != 0) out.add(write);
        if ((value & 1) != 0) out.add(execute);
    }
}
