package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import java.time.OffsetDateTime;

public record CreationTime(OffsetDateTime time) {

    public CreationTime(){
        this(OffsetDateTime.now());
    }
}
