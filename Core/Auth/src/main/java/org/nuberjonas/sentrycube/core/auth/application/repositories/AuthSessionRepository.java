package org.nuberjonas.sentrycube.core.auth.application.repositories;

import org.nuberjonas.sentrycube.core.auth.domain.entities.Session;

public interface AuthSessionRepository {

    void save(Session session);
}
