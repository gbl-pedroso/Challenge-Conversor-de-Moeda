package exceptions;

public class ConnectionApiFailException extends RuntimeException {
    public ConnectionApiFailException(String message) {
        super(message);
    }
}
