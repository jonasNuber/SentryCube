package org.nuberjonas.sentrycube.core.auth.domain.entities;

import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientId;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.CreationTime;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.ExpirationTime;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;

public class Session {

    private UserId userId;
    private ClientId clientId;
    private CreationTime creationTime;
    private ExpirationTime expirationTime;

}
