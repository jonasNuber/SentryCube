package org.nuberjonas.sentrycube.core.auth.domain.exceptions;

public class DisabledException extends Exception{
    public DisabledException() {
    }

    public DisabledException(String message) {
        super(message);
    }

    public DisabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
