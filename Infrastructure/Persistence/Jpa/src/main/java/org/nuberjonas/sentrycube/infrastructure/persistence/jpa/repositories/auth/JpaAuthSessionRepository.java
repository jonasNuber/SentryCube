package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.auth;

import org.nuberjonas.sentrycube.core.auth.application.repositories.AuthSessionRepository;
import org.nuberjonas.sentrycube.core.auth.domain.entities.Session;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.ConnectionInformation;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.CreationTime;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.ExpirationTime;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.SessionId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.TokenRepository;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaAuthSessionRepository implements AuthSessionRepository {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public JpaAuthSessionRepository(SessionRepository sessionRepository, UserRepository userRepository, ClientRepository clientRepository, TokenRepository tokenRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void save(Session session) {
        var persistenceSession = new org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables.Session();
        persistenceSession.setSessionId(session.getSessionId().id());
        persistenceSession.setCreationTime(session.getCreationTime().time());
        persistenceSession.setExpirationTime(session.getExpirationTime().time());
        persistenceSession.setIpAddress(session.getConnectionInformation().ipAddress());
        persistenceSession.setUserAgent(session.getConnectionInformation().userAgent());
        persistenceSession.setUser(userRepository.getReferenceById(session.getUserId().id()));
        persistenceSession.setClient(clientRepository.getReferenceById(session.getClientId().id()));

        sessionRepository.save(persistenceSession);
    }

    @Override
    public List<Session> findSessionsByUserId(UserId userId) {
        var persistedSessions = sessionRepository.findSessionsByUserId(userId.id());

        return persistedSessions.stream().map(session ->
                new Session.Loader(new SessionId(session.getSessionId()))
                        .clientId(new ClientId(session.getClient().getClientId()))
                        .userId(new UserId(session.getUser().getUserId()))
                        .creationTime(new CreationTime(session.getCreationTime()))
                        .expirationTime(new ExpirationTime(session.getExpirationTime()))
                        .connectionInformation(new ConnectionInformation(session.getUserAgent(), session.getIpAddress(), null))
                        .build())
                .toList();

    }
}
