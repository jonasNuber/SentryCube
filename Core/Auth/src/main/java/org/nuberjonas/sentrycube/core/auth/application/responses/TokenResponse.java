package org.nuberjonas.sentrycube.core.auth.application.responses;

import org.nuberjonas.sentrycube.core.auth.domain.entities.Token;

public record TokenResponse(String access_token, String refresh_token) {

    public TokenResponse(Token accessToken, Token refreshToken){
        this(accessToken.getEncodedToken().token(), refreshToken.getEncodedToken().token());
    }
}
