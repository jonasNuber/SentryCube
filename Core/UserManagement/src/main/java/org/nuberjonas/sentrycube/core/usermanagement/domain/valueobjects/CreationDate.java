package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import java.time.Instant;

public final class CreationDate {

    private final long timestamp;

    public CreationDate(){
        timestamp = Instant.now().toEpochMilli();
    }

    public CreationDate(long timestamp){
        this.timestamp = timestamp;
    }

    public long get(){
        return timestamp;
    }
}
