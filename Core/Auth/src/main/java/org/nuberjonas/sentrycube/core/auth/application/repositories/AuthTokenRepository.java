package org.nuberjonas.sentrycube.core.auth.application.repositories;

import org.nuberjonas.sentrycube.core.auth.domain.entities.Token;

public interface AuthTokenRepository {

    void save(Token token);
}
