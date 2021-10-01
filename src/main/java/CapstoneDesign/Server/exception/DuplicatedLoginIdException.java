package CapstoneDesign.Server.exception;

public class DuplicatedLoginIdException extends RuntimeException {
    public DuplicatedLoginIdException() {
        super();
    }

    public DuplicatedLoginIdException(String s) {
        super(s);
    }

    public DuplicatedLoginIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedLoginIdException(Throwable cause) {
        super(cause);
    }
}