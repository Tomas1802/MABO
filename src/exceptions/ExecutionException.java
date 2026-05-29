package exceptions;

public class ExecutionException extends RuntimeException {
    public ExecutionException(String msg, Throwable cause) { super(msg, cause); }
    public ExecutionException(String msg) { super(msg); }
}
