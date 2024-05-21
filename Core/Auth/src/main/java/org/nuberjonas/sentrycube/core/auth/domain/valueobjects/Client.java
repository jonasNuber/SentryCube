package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.RealmName;

public record Client(
        ClientCredentials credentials,
        RealmName realmName,
        Protocol protocol,
        GrantType grantType,
        Boolean enabled,
        ClientCredentials providedCredentials) {

    public Client addProvidedCredentials(ClientCredentials providedCredentials){
        return new Client(
                credentials,
                realmName,
                protocol,
                grantType,
                enabled,
                providedCredentials);
    }

    public boolean isValid(){
        return providedCredentials.areValid(credentials);
    }

    public boolean isInvalid() { return !isValid(); }

    public boolean disabled(){
        return !enabled;
    }


}
