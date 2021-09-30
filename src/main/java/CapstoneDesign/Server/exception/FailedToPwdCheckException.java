package CapstoneDesign.Server.exception;

public class FailedToPwdCheckException extends RuntimeException{
    public FailedToPwdCheckException() {
        super();
    }

    public FailedToPwdCheckException(String message) {
        super(message);
    }

    public FailedToPwdCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToPwdCheckException(Throwable cause) {
        super(cause);
    }

    protected FailedToPwdCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
