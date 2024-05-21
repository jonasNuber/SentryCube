package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.auth;

import org.nuberjonas.sentrycube.core.auth.application.repositories.AuthSessionRepository;
import org.nuberjonas.sentrycube.core.auth.domain.entities.Session;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.TokenRepository;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

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
        persistenceSession.setSessionTokens(session.getSessionTokens().stream().map(tokenId -> tokenRepository.getReferenceById(tokenId.id())).collect(Collectors.toSet()));

        sessionRepository.save(persistenceSession);
    }
}
