package org.nuberjonas.sentrycube.core.sharedkernel.exceptions;

public class PasswordHashException extends RuntimeException{
    public PasswordHashException() {
    }

    public PasswordHashException(String message) {
        super(message);
    }

    public PasswordHashException(String message, Throwable cause) {
        super(message, cause);
    }
}
