package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import java.util.Objects;

public record SignKeyId(Integer id) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignKeyId signKeyId = (SignKeyId) o;
        return Objects.equals(id, signKeyId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
