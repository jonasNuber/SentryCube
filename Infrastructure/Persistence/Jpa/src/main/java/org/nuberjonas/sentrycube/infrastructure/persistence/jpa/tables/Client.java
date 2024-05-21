package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.data.GrantType;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.data.Protocol;

import java.util.Set;
import java.util.UUID;


@Entity
public class Client {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID clientId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String clientSecret;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Protocol protocol;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GrantType grantType;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @OneToMany(mappedBy = "client")
    private Set<Session> clientSessions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realm_name", nullable = false)
    private Realm realm;

    @OneToMany(mappedBy = "client")
    private Set<ClientRole> clientRoles;

    @OneToMany(mappedBy = "client")
    private Set<RedirectUri> redirectUris;

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(final UUID clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(final Protocol protocol) {
        this.protocol = protocol;
    }

    public GrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(final GrantType grantType) {
        this.grantType = grantType;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(final AccessType accessType) {
        this.accessType = accessType;
    }

    public Set<Session> getClientSessions() {
        return clientSessions;
    }

    public void setClientSessions(final Set<Session> clientSessions) {
        this.clientSessions = clientSessions;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(final Realm realm) {
        this.realm = realm;
    }

    public Set<ClientRole> getClientRoles() {
        return clientRoles;
    }

    public void setClientRoles(final Set<ClientRole> clientRoles) {
        this.clientRoles = clientRoles;
    }

    public Set<RedirectUri> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(final Set<RedirectUri> redirectUris) {
        this.redirectUris = redirectUris;
    }
}
