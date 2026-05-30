package shell;

import logs.LoggerService;
import model.ShellResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShellCommandServiceTest {
    @Test
    void simulatesPowerShellWithoutRunningCommand() {
        ShellCommandService service = new ShellCommandService(new LoggerService("logs/test.log"));

        ShellResult result = service.execute("powershell", "Write-Output 'hola'", true);

        assertTrue(result.simulated());
        assertEquals("powershell", result.shell());
        assertEquals(0, result.exitCode());
    }

    @Test
    void blocksDangerousCommandsEvenInSimulation() {
        ShellCommandService service = new ShellCommandService(new LoggerService("logs/test.log"));

        try {
            service.execute("linux", "rm -rf /", true);
        } catch (exceptions.SecurityException ex) {
            assertTrue(ex.getMessage().contains("bloqueado"));
            return;
        }
        throw new AssertionError("Expected dangerous command to be blocked");
    }
}
