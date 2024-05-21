package org.nuberjonas.sentrycube.core.auth.domain.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.DisabledException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.GrantTypeUnsuportedException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.InvalidCredentialsException;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.*;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientSecret;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.SessionId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionTest {

    @Mock
    private Realm mockRealm;

    @Mock
    private Client mockClient;

    @Mock
    private User mockUser;

    @InjectMocks
    private Session session;

    @Test
    void testCreateSession_success() throws Exception {
        when(mockRealm.disabled()).thenReturn(false);
        when(mockClient.disabled()).thenReturn(false);
        when(mockClient.grantType()).thenReturn(GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS);
        when(mockClient.credentials()).thenReturn(new ClientCredentials(new ClientId(UUID.randomUUID()), new ClientSecret("someSecret")));
        when(mockUser.isInvalid(anyBoolean())).thenReturn(false);
        when(mockClient.isInvalid()).thenReturn(false);
        when(mockRealm.emailLogin()).thenReturn(true);
        when(mockRealm.refreshTokenLifespan()).thenReturn(60);

        ConnectionInformation connectionInformation = new ConnectionInformation("user-agent", "127.0.0.1", GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS);
        Session session = Session.createSession(mockRealm, mockClient, mockUser, connectionInformation);

        assertNotNull(session);
        assertEquals(connectionInformation, session.getConnectionInformation());
    }

    @Test
    void testCreateSession_realmDisabled() {
        when(mockRealm.disabled()).thenReturn(true);

        ConnectionInformation connectionInformation = new ConnectionInformation("user-agent", "127.0.0.1", GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS);

        assertThrows(DisabledException.class, () -> {
            Session.createSession(mockRealm, mockClient, mockUser, connectionInformation);
        });
    }

    @Test
    void testCreateSession_clientDisabled() {
        when(mockRealm.disabled()).thenReturn(false);
        when(mockClient.disabled()).thenReturn(true);
        when(mockClient.credentials()).thenReturn(new ClientCredentials(new ClientId(UUID.randomUUID()), new ClientSecret("someSecret")));

        ConnectionInformation connectionInformation = new ConnectionInformation("user-agent", "127.0.0.1", GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS);

        assertThrows(DisabledException.class, () -> {
            Session.createSession(mockRealm, mockClient, mockUser, connectionInformation);
        });
    }

    @Test
    void testCreateSession_invalidClientCredentials() {
        when(mockRealm.disabled()).thenReturn(false);
        when(mockClient.disabled()).thenReturn(false);
        when(mockClient.isInvalid()).thenReturn(true);
        when(mockClient.credentials()).thenReturn(new ClientCredentials(new ClientId(UUID.randomUUID()), new ClientSecret("someSecret")));

        ConnectionInformation connectionInformation = new ConnectionInformation("user-agent", "127.0.0.1", GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS);

        assertThrows(InvalidCredentialsException.class, () -> {
            Session.createSession(mockRealm, mockClient, mockUser, connectionInformation);
        });
    }

    @Test
    void testCreateSession_invalidUserCredentials() {
        when(mockRealm.disabled()).thenReturn(false);
        when(mockClient.disabled()).thenReturn(false);
        when(mockClient.isInvalid()).thenReturn(false);
        when(mockUser.isInvalid(anyBoolean())).thenReturn(true);

        ConnectionInformation connectionInformation = new ConnectionInformation("user-agent", "127.0.0.1", GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS);

        assertThrows(InvalidCredentialsException.class, () -> {
            Session.createSession(mockRealm, mockClient, mockUser, connectionInformation);
        });
    }

    @Test
    void testCreateSession_unsupportedGrantType() {
        when(mockRealm.disabled()).thenReturn(false);
        when(mockClient.disabled()).thenReturn(false);
        when(mockClient.isInvalid()).thenReturn(false);
        when(mockUser.isInvalid(anyBoolean())).thenReturn(false);
        when(mockClient.grantType()).thenReturn(GrantType.CLIENT_CREDENTIALS);

        ConnectionInformation connectionInformation = new ConnectionInformation("user-agent", "127.0.0.1", GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS);

        assertThrows(GrantTypeUnsuportedException.class, () -> {
            Session.createSession(mockRealm, mockClient, mockUser, connectionInformation);
        });
    }

    @Test
    void testGetSessionId() {
        SessionId sessionId = new SessionId();
        Session session = new Session.Loader(sessionId).build();
        assertEquals(sessionId, session.getSessionId());
    }

    @Test
    void testGetUserId() {
        UserId userId = new UserId();
        Session session = new Session.Loader(new SessionId()).userId(userId).build();
        assertEquals(userId, session.getUserId());
    }

    @Test
    void testGetClientId() {
        ClientId clientId = new ClientId();
        Session session = new Session.Loader(new SessionId()).clientId(clientId).build();
        assertEquals(clientId, session.getClientId());
    }

    @Test
    void testGetCreationTime() {
        CreationTime creationTime = new CreationTime();
        Session session = new Session.Loader(new SessionId()).creationTime(creationTime).build();
        assertEquals(creationTime, session.getCreationTime());
    }

}