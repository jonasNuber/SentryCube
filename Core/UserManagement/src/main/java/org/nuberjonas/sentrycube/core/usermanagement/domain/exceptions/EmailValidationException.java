package org.nuberjonas.sentrycube.core.usermanagement.domain.exceptions;

public class EmailValidationException extends RuntimeException {
    public EmailValidationException() {
    }

    public EmailValidationException(String message) {
        super(message);
    }

    public EmailValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
