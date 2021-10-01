package CapstoneDesign.Server.exception;

public class DuplicatedMenuException extends RuntimeException {
    public DuplicatedMenuException() {
        super();
    }

    public DuplicatedMenuException(String message) {
        super(message);
    }

    public DuplicatedMenuException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedMenuException(Throwable cause) {
        super(cause);
    }

    protected DuplicatedMenuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
