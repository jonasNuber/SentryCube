package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.nuberjonas.sentrycube.core.sharedkernel.behaviours.PasswordVerification;

public record Password(String providedPassword, String passwordHash, String salt) implements PasswordVerification {

    public boolean isValid(){
        return verify(passwordHash, providedPassword, salt);
    }
}
