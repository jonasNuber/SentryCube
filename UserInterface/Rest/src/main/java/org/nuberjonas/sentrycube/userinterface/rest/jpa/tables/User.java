package org.nuberjonas.sentrycube.userinterface.rest.jpa.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.nuberjonas.sentrycube.userinterface.rest.jpa.data.Status;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;


@Entity
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String passwordSalt;

    @Column(nullable = false)
    private OffsetDateTime creationTime;

    @Column(nullable = false)
    private OffsetDateTime lastLogin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user")
    private Set<Session> userSessions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realm_name", nullable = false)
    private Realm realm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_data_id")
    private PersonalData personalData;

    @OneToMany(mappedBy = "user")
    private Set<Attribute> attributes;

    @OneToMany(mappedBy = "user")
    private Set<UserRealmRole> realmRoles;

    @OneToMany(mappedBy = "user")
    private Set<UserClientRole> clientRoles;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(final UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(final String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(final String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public OffsetDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(final OffsetDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public OffsetDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(final OffsetDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public Set<Session> getUserSessions() {
        return userSessions;
    }

    public void setUserSessions(final Set<Session> userSessions) {
        this.userSessions = userSessions;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(final Realm realm) {
        this.realm = realm;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(final PersonalData personalData) {
        this.personalData = personalData;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(final Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Set<UserRealmRole> getRealmRoles() {
        return realmRoles;
    }

    public void setRealmRoles(final Set<UserRealmRole> realmRoles) {
        this.realmRoles = realmRoles;
    }

    public Set<UserClientRole> getClientRoles() {
        return clientRoles;
    }

    public void setClientRoles(final Set<UserClientRole> clientRoles) {
        this.clientRoles = clientRoles;
    }
}
