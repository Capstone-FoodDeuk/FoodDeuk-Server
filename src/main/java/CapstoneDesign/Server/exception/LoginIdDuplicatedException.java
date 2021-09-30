package CapstoneDesign.Server.exception;

public class LoginIdDuplicatedException extends RuntimeException {
    public LoginIdDuplicatedException() {
        super();
    }

    public LoginIdDuplicatedException(String s) {
        super(s);
    }

    public LoginIdDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginIdDuplicatedException(Throwable cause) {
        super(cause);
    }
}