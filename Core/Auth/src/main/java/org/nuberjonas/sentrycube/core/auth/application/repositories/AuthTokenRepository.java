package org.nuberjonas.sentrycube.core.auth.application.repositories;

import org.nuberjonas.sentrycube.core.auth.domain.entities.Token;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.SessionId;

import java.util.List;

public interface AuthTokenRepository {

    List<Token> findTokensBySessionId(SessionId sessionId);
    void save(Token token);
}
