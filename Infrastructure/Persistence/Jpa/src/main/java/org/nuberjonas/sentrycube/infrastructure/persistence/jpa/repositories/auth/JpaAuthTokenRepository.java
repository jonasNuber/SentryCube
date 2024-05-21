package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.auth;

import org.nuberjonas.sentrycube.core.auth.application.repositories.AuthTokenRepository;
import org.nuberjonas.sentrycube.core.auth.domain.entities.Token;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.data.TokenType;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAuthTokenRepository implements AuthTokenRepository {

    private final TokenRepository tokenRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public JpaAuthTokenRepository(TokenRepository tokenRepository, SessionRepository sessionRepository) {
        this.tokenRepository = tokenRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void save(Token token) {
        var persistenceToken = new org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables.Token();
        persistenceToken.setTokenId(token.getTokenId().id());
        persistenceToken.setEncodedToken(token.getEncodedToken().token());
        persistenceToken.setKeyId(token.getSignKeyId().id());
        persistenceToken.setTokenType(TokenType.valueOf(token.getTokenType().name()));
        persistenceToken.setCreationTime(token.getCreationTime().time());
        persistenceToken.setExpirationTime(token.getExpirationTime().time());
        persistenceToken.setSession(sessionRepository.getReferenceById(token.getSessionId().id()));

        tokenRepository.save(persistenceToken);
    }
}
