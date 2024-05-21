package org.nuberjonas.sentrycube.core.auth.domain.exceptions;

public class GrantTypeUnsuportedException extends Exception{
    public GrantTypeUnsuportedException() {
    }

    public GrantTypeUnsuportedException(String message) {
        super(message);
    }

    public GrantTypeUnsuportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
