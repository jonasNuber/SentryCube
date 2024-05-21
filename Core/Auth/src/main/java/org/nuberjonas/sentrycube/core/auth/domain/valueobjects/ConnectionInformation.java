package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

public record ConnectionInformation(String userAgent, String ipAddress, GrantType grantType) {
}
