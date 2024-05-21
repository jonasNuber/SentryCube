package org.nuberjonas.sentrycube.core.auth.domain.entities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.*;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.SessionId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.TokenId;

import java.security.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Token {
    private final TokenId tokenId;
    private final SessionId sessionId;
    private final EncodedToken encodedToken;
    private final SignKeyId signKeyId;
    private final TokenType tokenType;
    private final CreationTime creationTime;
    private final ExpirationTime expirationTime;

    private Token(TokenId tokenId, SessionId sessionId, EncodedToken encodedToken, SignKeyId signKeyId, TokenType tokenType, CreationTime creationTime, ExpirationTime expirationTime) {
        this.tokenId = tokenId;
        this.sessionId = sessionId;
        this.encodedToken = encodedToken;
        this.signKeyId = signKeyId;
        this.tokenType = tokenType;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
    }

    private Token(Loader loader) {
        this.tokenId = loader.tokenId;
        this.sessionId = loader.sessionId;
        this.encodedToken = loader.encodedToken;
        this.signKeyId = loader.signKeyId;
        this.tokenType = loader.tokenType;
        this.creationTime = loader.creationTime;
        this.expirationTime = loader.expirationTime;
    }

    public static Token createAccessToken(Session session, User user, Realm realm) {
        return createToken(session, user, realm, TokenType.ACCESS, realm.accessTokenLifeSpan());
    }

    public static Token createRefreshToken(Session session, User user, Realm realm) {
        return createToken(session, user, realm, TokenType.REFRESH, realm.refreshTokenLifespan());
    }

    private static Token createToken(Session session, User user, Realm realm, TokenType tokenType, Integer tokenLifespan) {
        var keyId = new SignKeyId(RsaKeyManager.getRandomKey());
        var creationTime = session.getCreationTime();
        var expirationTime = new ExpirationTime(creationTime, tokenLifespan);

        var jwtBuilder = Jwts.builder()
                .issuer("SentryCube")
                .subject(user.userCredentials().userName().name())
                .issuedAt(Date.from(creationTime.time().toInstant()))
                .expiration(Date.from(expirationTime.time().toInstant()))
                .claim("user_id", user.userId().id())
                .claim("session_id", session.getSessionId())
                .claim("client_id", session.getClientId());

        if (tokenType == TokenType.ACCESS) {
            jwtBuilder
                    .claim("realm_roles", user.realmRoles())
                    .claim("client_roles", user.clientRoles());
        }

        var token = jwtBuilder
                .signWith(RsaKeyManager.getPrivateKey(keyId), SignatureAlgorithm.RS256)
                .compact();

        return new Token(
                new TokenId(),
                session.getSessionId(),
                new EncodedToken(token),
                keyId,
                tokenType,
                creationTime,
                expirationTime
        );
    }

    public TokenId getTokenId() {
        return tokenId;
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    public EncodedToken getEncodedToken() {
        return encodedToken;
    }

    public SignKeyId getSignKeyId() {
        return signKeyId;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public CreationTime getCreationTime() {
        return creationTime;
    }

    public ExpirationTime getExpirationTime() {
        return expirationTime;
    }

    public static class Loader{
        private final TokenId tokenId;
        private SessionId sessionId;
        private EncodedToken encodedToken;
        private SignKeyId signKeyId;
        private TokenType tokenType;
        private CreationTime creationTime;
        private ExpirationTime expirationTime;

        public Loader(TokenId tokenId) {
            this.tokenId = tokenId;
        }

        public Loader sessionId(SessionId sessionId){
            this.sessionId = sessionId;

            return this;
        }

        public Loader encodedToken(EncodedToken encodedToken){
            this.encodedToken = encodedToken;

            return this;
        }

        public Loader signKeyId(SignKeyId signKeyId){
            this.signKeyId = signKeyId;

            return this;
        }

        public Loader tokenType(TokenType tokenType){
            this.tokenType = tokenType;

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

        public Token build(){
            return new Token(this);
        }
    }

    // this exists only for simplicity
    private static class RsaKeyManager {
        private static final Map<Integer, KeyPair> keyPairs = new HashMap<>();
        private static final Random random = new Random();
        private static final Integer keyCount = 10;

        static {
            try {
                for (int i = 0; i < keyCount; i++) {
                    keyPairs.put(i, generateKeyPair());
                }
            } catch (Exception exception) {
                throw new RuntimeException("Failed to initialize RSA key pairs", exception);
            }
        }

        private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        }

        static Integer getRandomKey() {
            return random.nextInt(keyCount);
        }

        static PublicKey getPublicKey(SignKeyId keyId) {
            KeyPair keyPair = keyPairs.get(keyId.id());
            if (keyPair != null) {
                return keyPair.getPublic();
            } else {
                throw new IllegalArgumentException("Key ID not found");
            }
        }

        static PrivateKey getPrivateKey(SignKeyId keyId) {
            KeyPair keyPair = keyPairs.get(keyId.id());
            if (keyPair != null) {
                return keyPair.getPrivate();
            } else {
                throw new IllegalArgumentException("Key ID not found");
            }
        }
    }
}
