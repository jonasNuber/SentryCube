package org.nuberjonas.sentrycube.core.auth.application.repositories;

import org.nuberjonas.sentrycube.core.auth.domain.entities.Session;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;

import java.util.List;

public interface AuthSessionRepository {

    void save(Session session);

    List<Session> findSessionsByUserId(UserId userId);
}
