package org.nuberjonas.sentrycube.userinterface.rest;

import org.nuberjonas.sentrycube.core.auth.application.AuthenticationService;
import org.nuberjonas.sentrycube.core.auth.application.repositories.*;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.auth.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthentivationServiceConfiguration {

    private final AuthClientRepository clientRepository;
    private final AuthRealmRepository realmRepository;
    private final AuthUserRepository userRepository;
    private final AuthSessionRepository sessionRepository;
    private final AuthTokenRepository tokenRepository;

    public AuthentivationServiceConfiguration(JpaAuthClientRepository clientRepository, JpaAuthRealmRepository realmRepository, JpaAuthUserRepository userRepository, JpaAuthSessionRepository sessionRepository, JpaAuthTokenRepository tokenRepository) {
        this.clientRepository = clientRepository;
        this.realmRepository = realmRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.tokenRepository = tokenRepository;
    }

    @Bean
    public AuthenticationService authenticationService(){
        return new AuthenticationService(clientRepository, realmRepository, userRepository, sessionRepository, tokenRepository);
    }
}
