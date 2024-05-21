package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import java.util.Objects;

public record EncodedToken(String token) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncodedToken that = (EncodedToken) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
