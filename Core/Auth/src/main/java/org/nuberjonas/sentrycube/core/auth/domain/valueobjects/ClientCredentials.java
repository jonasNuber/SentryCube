package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientSecret;

public record ClientCredentials(ClientId clientId, ClientSecret clientSecret) {
    public boolean areValid(ClientCredentials providedCredentials){
        return clientId.equals(providedCredentials.clientId) && clientSecret.equals(providedCredentials.clientSecret);
    }
}
