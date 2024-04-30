package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.data.TokenType;

import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
public class Token {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID tokenId;

    @Column(nullable = false, length = 2024)
    private String encodedToken;

    @Column(nullable = false)
    private UUID keyId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column(nullable = false)
    private OffsetDateTime creationTime;

    @Column(nullable = false)
    private OffsetDateTime expirationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    public UUID getTokenId() {
        return tokenId;
    }

    public void setTokenId(final UUID tokenId) {
        this.tokenId = tokenId;
    }

    public String getEncodedToken() {
        return encodedToken;
    }

    public void setEncodedToken(final String encodedToken) {
        this.encodedToken = encodedToken;
    }

    public UUID getKeyId() {
        return keyId;
    }

    public void setKeyId(final UUID keyId) {
        this.keyId = keyId;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(final TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public OffsetDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(final OffsetDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public OffsetDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(final OffsetDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(final Session session) {
        this.session = session;
    }
}
