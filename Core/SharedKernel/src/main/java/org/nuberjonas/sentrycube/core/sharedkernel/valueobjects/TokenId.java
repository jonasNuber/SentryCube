package org.nuberjonas.sentrycube.core.sharedkernel.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record TokenId(UUID id) {

    public TokenId (){ this(UUID.randomUUID()); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenId tokenId = (TokenId) o;
        return Objects.equals(id, tokenId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
