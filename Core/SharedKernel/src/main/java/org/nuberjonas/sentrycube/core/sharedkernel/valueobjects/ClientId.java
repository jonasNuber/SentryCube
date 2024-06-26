package org.nuberjonas.sentrycube.core.sharedkernel.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record ClientId(UUID id) {

    public ClientId(){
        this( UUID.randomUUID() );
    }

    public ClientId(String id) { this(UUID.fromString(id)); }

    public boolean isValid(ClientId providedId){
        return this.equals(providedId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientId clientId = (ClientId) o;
        return Objects.equals(id, clientId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
