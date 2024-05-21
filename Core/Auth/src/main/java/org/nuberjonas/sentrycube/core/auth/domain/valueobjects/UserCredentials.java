package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

public record UserCredentials(UserName userName, Email email, Password password) {

    public boolean areValid(UserCredentials providedCredentials, boolean emailLogin){
        return emailLogin ? areValid(providedCredentials.email, providedCredentials.password) : areValid(providedCredentials.userName, providedCredentials.password);
    }

    public boolean areValid(UserName providedUserName, Password providedPassword){
        return userName.isValid(providedUserName) && password.isValid(providedPassword);
    }

    public boolean areValid(Email email, Password providedPassword){
        return email.isValid(email) && password.isValid(providedPassword);
    }
}
