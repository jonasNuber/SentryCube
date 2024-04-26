package org.nuberjonas.sentrycube.core.sharedkernel.valueobjects;

import java.util.UUID;

public record ClientId(UUID id) {

    public ClientId(){
        this( UUID.randomUUID() );
    }
}
