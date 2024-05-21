package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import java.time.OffsetDateTime;

public record ExpirationTime(OffsetDateTime time) {

    public ExpirationTime(CreationTime creationTime, Integer offset){
        this(creationTime.time().plusMinutes(offset));
    }

    public boolean isReached(){
        return time.isBefore(OffsetDateTime.now());
    }
}
