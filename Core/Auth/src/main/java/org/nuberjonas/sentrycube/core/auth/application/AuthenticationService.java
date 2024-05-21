package org.nuberjonas.sentrycube.core.auth.application;

import org.nuberjonas.sentrycube.core.auth.application.exceptions.ClientNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.RealmNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.UserNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.repositories.*;
import org.nuberjonas.sentrycube.core.auth.application.responses.TokenResponse;
import org.nuberjonas.sentrycube.core.auth.domain.entities.Session;
import org.nuberjonas.sentrycube.core.auth.domain.entities.Token;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.DisabledException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.GrantTypeUnsuportedException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.InvalidCredentialsException;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.*;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientSecret;

public class AuthenticationService {

    private final AuthClientRepository clientRepository;
    private final AuthRealmRepository realmRepository;
    private final AuthUserRepository userRepository;
    private final AuthSessionRepository sessionRepository;
    private final AuthTokenRepository tokenRepository;

    public AuthenticationService(AuthClientRepository clientRepository, AuthRealmRepository realmRepository, AuthUserRepository userRepository, AuthSessionRepository sessionRepository, AuthTokenRepository tokenRepository) {
        this.clientRepository = clientRepository;
        this.realmRepository = realmRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.tokenRepository = tokenRepository;
    }

    public TokenResponse authenticateRessourceOwner(AuthenticationRequest request) throws InvalidCredentialsException, DisabledException, GrantTypeUnsuportedException, ClientNotFoundException, RealmNotFoundException, UserNotFoundException {
        var client = clientRepository.findById(new ClientId(request.clientId()))
                .orElseThrow(() -> new ClientNotFoundException(String.format("The client with the id '%s' does not exist", request.clientId())));
        var realmName = client.realmName().name();
        var realm = realmRepository.findByName(client.realmName())
                .orElseThrow(() -> new RealmNotFoundException(String.format("The realm with the name `%s` could not be found", realmName)));
        var user = userRepository.findByUsernameAndRealmname(new UserName(request.username()), realm.realmName())
                .orElseThrow(() ->  new UserNotFoundException(String.format("The user with the username '%s' could not be found", request.username())));

        client = client.addProvidedCredentials(new ClientCredentials(new ClientId(request.clientId()), new ClientSecret(request.clientSecret())));
        user = user.addProvidedCredentials(new UserCredentials(new UserName(request.username()), new Email(request.username()), new Password(request.password(), null)));

        var session = Session.createSession(realm, client, user, new ConnectionInformation(request.userAgent(), request.ipAddress(), GrantType.valueOf(request.grantType())));
        var accessToken = Token.createAccessToken(session, user, realm);
        var refreshToken = Token.createRefreshToken(session, user, realm);
        //session.addSessionToken(accessToken, refreshToken);

        sessionRepository.save(session);
        tokenRepository.save(accessToken);
        tokenRepository.save(refreshToken);

        return new TokenResponse(accessToken, refreshToken);
    }

}
