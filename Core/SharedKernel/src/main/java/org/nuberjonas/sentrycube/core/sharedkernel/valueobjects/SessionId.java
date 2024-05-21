package org.nuberjonas.sentrycube.core.sharedkernel.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record SessionId(UUID id) {

    public SessionId(){ this(UUID.randomUUID()); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionId sessionId = (SessionId) o;
        return Objects.equals(id, sessionId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
