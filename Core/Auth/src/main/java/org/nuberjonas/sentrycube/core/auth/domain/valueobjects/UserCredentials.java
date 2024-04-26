package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

public record UserCredentials(UserName userName, Email email, Password password) {

    public boolean areValid(boolean emailLogin){
        return userNameOrEmailIsValid(emailLogin) && password().isValid();
    }

    private boolean userNameOrEmailIsValid(boolean emailLogin){
        return emailLogin ? (email.isValid() || email.equalsUsername(userName)) : userName.isValid();
    }
}
