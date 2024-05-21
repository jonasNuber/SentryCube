package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;

import java.util.List;

public record User(
        UserId userId,
        UserCredentials userCredentials,
        List<RealmRole> realmRoles,
        List<ClientRole> clientRoles,
        UserCredentials providedCredentials) {

    public User addProvidedCredentials(UserCredentials providedCredentials){
        return new User(
                userId,
                userCredentials,
                realmRoles,
                clientRoles,
                providedCredentials
        );
    }

    public boolean isValid(boolean emailLogin){
        return userCredentials.areValid(providedCredentials, emailLogin);
    }

    public boolean isInvalid(boolean emailLogin){
        return !isValid(emailLogin);
    }
}
