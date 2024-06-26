package org.nuberjonas.sentrycube.core.sharedkernel.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record UserId(UUID id) {

    public UserId(){
        this(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(id, userId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
