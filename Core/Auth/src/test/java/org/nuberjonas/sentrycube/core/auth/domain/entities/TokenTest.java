package org.nuberjonas.sentrycube.core.auth.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.*;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.SessionId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TokenTest {

    private Session session;
    private User user;
    private Realm realm;

    @BeforeEach
    void setUp() {
        session = mock(Session.class);
        user = mock(User.class);
        realm = mock(Realm.class);

        var creationTime = new CreationTime(OffsetDateTime.now());

        when(session.getSessionId()).thenReturn(new SessionId());
        when(session.getCreationTime()).thenReturn(creationTime);

        when(user.userCredentials()).thenReturn(new UserCredentials(new UserName("username"), new Email("user@example.com"), new Password("password", null)));
        when(user.userId()).thenReturn(new UserId());
    }

    @Test
    void testCreateAccessToken() {
        try (MockedStatic<Token.RsaKeyManager> rsaKeyManagerMockedStatic = mockStatic(Token.RsaKeyManager.class)) {
            mockRsaKeyManager(rsaKeyManagerMockedStatic);

            Token accessToken = Token.createAccessToken(session, user, realm);

            assertNotNull(accessToken);
            assertEquals(TokenType.ACCESS, accessToken.getTokenType());
            assertTokenProperties(accessToken);
        }
    }

    @Test
    void testCreateRefreshToken() {
        try (MockedStatic<Token.RsaKeyManager> rsaKeyManagerMockedStatic = mockStatic(Token.RsaKeyManager.class)) {
            mockRsaKeyManager(rsaKeyManagerMockedStatic);

            Token refreshToken = Token.createRefreshToken(session, user, realm);

            assertNotNull(refreshToken);
            assertEquals(TokenType.REFRESH, refreshToken.getTokenType());
            assertTokenProperties(refreshToken);
        }
    }

    private void mockRsaKeyManager(MockedStatic<Token.RsaKeyManager> rsaKeyManagerMockedStatic) {
        rsaKeyManagerMockedStatic.when(Token.RsaKeyManager::getRandomKey).thenReturn(0);
        rsaKeyManagerMockedStatic.when(() -> Token.RsaKeyManager.getPrivateKey(any(SignKeyId.class))).thenReturn(generateKeyPair().getPrivate());
        rsaKeyManagerMockedStatic.when(() -> Token.RsaKeyManager.getPublicKey(any(SignKeyId.class))).thenReturn(generateKeyPair().getPublic());
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }

    private void assertTokenProperties(Token token) {
        assertNotNull(token.getTokenId());
        assertNotNull(token.getSessionId());
        assertNotNull(token.getEncodedToken());
        assertNotNull(token.getSignKeyId());
        assertNotNull(token.getCreationTime());
        assertNotNull(token.getExpirationTime());
    }
}