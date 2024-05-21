package org.nuberjonas.sentrycube.core.sharedkernel.valueobjects;

import java.util.Objects;

public record ClientSecret(String secret) {

    public boolean isValid(ClientSecret providedSecret){
        return this.equals(providedSecret);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientSecret that = (ClientSecret) o;
        return Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secret);
    }
}
