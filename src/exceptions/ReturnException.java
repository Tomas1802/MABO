package exceptions;

public class ReturnException extends RuntimeException {
    public final Object value;

    public ReturnException(Object value) {
        super(null, null, true, false); // no mensaje, no causa, suprimible, sin stack trace
        this.value = value;
    }
}
