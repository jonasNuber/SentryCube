package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

public record AuthenticationRequest(
        String username,
        String password,
        String clientId,
        String clientSecret,
        String userAgent,
        String ipAddress,
        String grantType
) {
}
