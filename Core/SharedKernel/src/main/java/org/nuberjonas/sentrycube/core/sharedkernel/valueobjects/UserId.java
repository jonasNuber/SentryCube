package org.nuberjonas.sentrycube.core.sharedkernel.valueobjects;

import java.util.UUID;

public record UserId(UUID id) {

    public UserId(){
        this(UUID.randomUUID());
    }
}