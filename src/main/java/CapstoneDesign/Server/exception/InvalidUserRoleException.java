package CapstoneDesign.Server.exception;

public class InvalidUserRoleException extends IllegalArgumentException {
    public InvalidUserRoleException() {
        super();
    }

    public InvalidUserRoleException(String s) {
        super(s);
    }

    public InvalidUserRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserRoleException(Throwable cause) {
        super(cause);
    }
}