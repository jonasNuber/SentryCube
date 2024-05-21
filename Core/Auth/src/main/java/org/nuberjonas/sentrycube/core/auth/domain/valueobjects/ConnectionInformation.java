package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import java.util.Objects;

public record ConnectionInformation(String userAgent, String ipAddress, GrantType grantType) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionInformation that = (ConnectionInformation) o;
        return Objects.equals(userAgent, that.userAgent) && Objects.equals(ipAddress, that.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAgent, ipAddress);
    }
}
