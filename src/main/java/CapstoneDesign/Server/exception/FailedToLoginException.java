package CapstoneDesign.Server.exception;

public class FailedToLoginException extends IllegalArgumentException {
    public FailedToLoginException() {
        super();
    }

    public FailedToLoginException(String s) {
        super(s);
    }

    public FailedToLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToLoginException(Throwable cause) {
        super(cause);
    }
}
