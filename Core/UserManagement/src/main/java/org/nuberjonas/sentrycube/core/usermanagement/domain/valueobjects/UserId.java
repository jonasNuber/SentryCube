package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import java.util.UUID;

public final class UserId {

    private final UUID id;

    public UserId(UUID id) {
        this.id = id;
    }

    public UserId(){
        id = UUID.randomUUID();
    }

    public UUID get(){
        return id;
    }
}
