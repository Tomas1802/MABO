package model;

public class ShellResult {
    private final String shell;
    private final String command;
    private final int exitCode;
    private final String stdout;
    private final String stderr;
    private final boolean simulated;

    public ShellResult(String shell, String command, int exitCode, String stdout, String stderr, boolean simulated) {
        this.shell = shell;
        this.command = command;
        this.exitCode = exitCode;
        this.stdout = stdout == null ? "" : stdout;
        this.stderr = stderr == null ? "" : stderr;
        this.simulated = simulated;
    }

    public String shell() { return shell; }
    public String command() { return command; }
    public int exitCode() { return exitCode; }
    public String stdout() { return stdout; }
    public String stderr() { return stderr; }
    public boolean simulated() { return simulated; }

    @Override
    public String toString() {
        return "ShellResult{" +
                "shell='" + shell + '\'' +
                ", exitCode=" + exitCode +
                ", simulated=" + simulated +
                ", stdout='" + stdout.replace("\n", "\\n") + '\'' +
                ", stderr='" + stderr.replace("\n", "\\n") + '\'' +
                '}';
    }
}
