package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.nuberjonas.sentrycube.core.sharedkernel.behaviours.PasswordVerification;

public record Password(String passwordText, String salt) implements PasswordVerification {

    public boolean isValid(Password password){
        return verify(passwordText, password.passwordText, salt);
    }
}
