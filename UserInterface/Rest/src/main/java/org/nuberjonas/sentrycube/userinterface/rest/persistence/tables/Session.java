package org.nuberjonas.sentrycube.userinterface.rest.persistence.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;


@Entity
public class Session {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID sessionId;

    @Column(nullable = false)
    private OffsetDateTime creationTime;

    @Column(nullable = false)
    private OffsetDateTime expirationTime;

    @Column(length = 60)
    private String ipAddress;

    @Column(length = 255)
    private String userAgent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private SentryCubeUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "session")
    private Set<Token> sessionTokens;

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(final UUID sessionId) {
        this.sessionId = sessionId;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }

    public SentryCubeUser getUser() {
        return user;
    }

    public void setUser(final SentryCubeUser user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(final Client client) {
        this.client = client;
    }

    public Set<Token> getSessionTokens() {
        return sessionTokens;
    }

    public void setSessionTokens(final Set<Token> sessionTokens) {
        this.sessionTokens = sessionTokens;
    }
}
