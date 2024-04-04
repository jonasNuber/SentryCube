package org.nuberjonas.sentrycube.core.usermanagement.domain.exceptions;

public class UserCreationException extends RuntimeException{
    public UserCreationException() {
    }

    public UserCreationException(String message) {
        super(message);
    }

    public UserCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
