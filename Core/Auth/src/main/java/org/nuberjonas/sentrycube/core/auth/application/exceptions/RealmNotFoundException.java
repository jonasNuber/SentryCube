package org.nuberjonas.sentrycube.core.auth.application.exceptions;

public class RealmNotFoundException extends Exception{
    public RealmNotFoundException() {
    }

    public RealmNotFoundException(String message) {
        super(message);
    }

    public RealmNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
