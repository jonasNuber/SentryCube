package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import java.time.Instant;

public record CreationDate(long timestamp) {

    public CreationDate(){
        this(Instant.now().toEpochMilli());
    }
}
