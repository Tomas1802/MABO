package security;

import exceptions.SecurityException;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SecurityValidatorTest {
    @Test
    void allowsPathsInsideUserProfileByDefault() {
        Path userProjectPath = Paths.get(System.getProperty("user.home"))
                .resolve("Documents")
                .resolve("TOMAS")
                .resolve("Prueba");

        assertDoesNotThrow(() -> SecurityValidator.validatePath(userProjectPath));
    }

    @Test
    void blocksCriticalPathsEvenIfTheyAreUnderAnAllowedRoot() {
        Path criticalPath = Paths.get(System.getProperty("user.home")).resolve("System32");

        assertThrows(SecurityException.class, () -> SecurityValidator.validatePath(criticalPath));
    }
}
