package org.nuberjonas.sentrycube.core.auth.domain.entities;

import org.nuberjonas.sentrycube.core.auth.domain.exceptions.DisabledException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.GrantTypeUnsuportedException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.InvalidCredentialsException;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.*;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.SessionId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;

public class Session {
    private final SessionId sessionId;
    private final UserId userId;
    private final ClientId clientId;
    private final CreationTime creationTime;
    private final ExpirationTime expirationTime;
    private final ConnectionInformation connectionInformation;

    private Session(SessionId sessionId, UserId userId, ClientId clientId, CreationTime creationTime, ExpirationTime expirationTime, ConnectionInformation connectionInformation) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.clientId = clientId;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
        this.connectionInformation = connectionInformation;
    }

    private Session(Loader loader){
        this.sessionId = loader.sessionId;
        this.userId = loader.userId;
        this.clientId = loader.clientId;
        this.creationTime = loader.creationTime;
        this.expirationTime = loader.expirationTime;
        this.connectionInformation = loader.connectionInformation;
    }

    public static Session createSession(Realm realm, Client client, User user, ConnectionInformation connectionInformation) throws DisabledException, InvalidCredentialsException, GrantTypeUnsuportedException {
        checkIfEnabled(realm, client);
        checkIfValid(client, user, realm.emailLogin());

        if(client.grantType().equals(connectionInformation.grantType())){
            var creationTime = new CreationTime();

            return new Session(
                    new SessionId(),
                    user.userId(),
                    client.credentials().clientId(),
                    creationTime,
                    new ExpirationTime(creationTime, realm.refreshTokenLifespan() + 1),
                    connectionInformation);
        } else {
            throw new GrantTypeUnsuportedException(String.format("The grant type: %s is not supported by the client", connectionInformation.grantType()));
        }
    }

    private static void checkIfEnabled(Realm realm, Client client) throws DisabledException {
        if(realm.disabled()){
            throw new DisabledException(String.format("The Realm '%s' is disabled.", realm.realmName()));
        }

        if(client.disabled()){
            throw new DisabledException(String.format("The Client with the Id '%s' is disabled.", client.credentials().clientId().id()));
        }
    }

    private static void checkIfValid(Client client, User user, boolean emailLogin) throws InvalidCredentialsException {
        if(client.isInvalid()){
            throw new InvalidCredentialsException(String.format("The client credentials for the client with the id '%s' are invalid", client.credentials().clientId()));
        }

        if(user.isInvalid(emailLogin)){
            throw new InvalidCredentialsException("The username or password are wrong.");
        }
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    public UserId getUserId() {
        return userId;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public CreationTime getCreationTime() {
        return creationTime;
    }

    public ExpirationTime getExpirationTime() {
        return expirationTime;
    }

    public ConnectionInformation getConnectionInformation() {
        return connectionInformation;
    }

    public static class Loader{
        private final SessionId sessionId;
        private UserId userId;
        private ClientId clientId;
        private CreationTime creationTime;
        private ExpirationTime expirationTime;
        private ConnectionInformation connectionInformation;

        public Loader(SessionId sessionId) {
            this.sessionId = sessionId;
        }

        public Loader userId(UserId userId){
            this.userId = userId;

            return this;
        }

        public Loader clientId(ClientId clientId){
            this.clientId = clientId;

            return this;
        }

        public Loader creationTime(CreationTime creationTime){
            this.creationTime = creationTime;

            return this;
        }

        public Loader expirationTime(ExpirationTime expirationTime){
            this.expirationTime = expirationTime;

            return this;
        }

        public Loader connectionInformation(ConnectionInformation connectionInformation){
            this.connectionInformation = connectionInformation;

            return this;
        }

        public Session build(){
            return new Session(this);
        }
    }
}
