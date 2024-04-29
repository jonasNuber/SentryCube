package org.nuberjonas.sentrycube.userinterface.rest.jpa.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;

import java.util.Set;


@Entity
public class Realm {

    @Id
    @Column(nullable = false, updatable = false)
    private String realmName;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private Boolean userManageable;

    @Column(nullable = false)
    private Boolean registrable;

    @Column(nullable = false)
    private Boolean verifyEmail;

    @Column(nullable = false)
    private Boolean emailLogin;

    @Column(nullable = false)
    @Min(value = 1, message = "Lifespan must be greater than 0")
    private Integer accessTokenLifespan;

    @Column(nullable = false)
    @Min(value = 1, message = "Lifespan must be greater than 0")
    private Integer refreshTokenLifespan;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "realm")
    private Set<SentryCubeUser> users;

    @OneToMany(mappedBy = "realm")
    private Set<Client> clients;

    @OneToMany(mappedBy = "realm")
    private Set<RealmRole> realmRoles;

    public String getRealmName() {
        return realmName;
    }

    public void setRealmName(final String realmName) {
        this.realmName = realmName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getUserManageable() {
        return userManageable;
    }

    public void setUserManageable(final Boolean userManageable) {
        this.userManageable = userManageable;
    }

    public Boolean getRegistrable() {
        return registrable;
    }

    public void setRegistrable(final Boolean registrable) {
        this.registrable = registrable;
    }

    public Boolean getVerifyEmail() {
        return verifyEmail;
    }

    public void setVerifyEmail(final Boolean verifyEmail) {
        this.verifyEmail = verifyEmail;
    }

    public Boolean getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(final Boolean emailLogin) {
        this.emailLogin = emailLogin;
    }

    public Integer getAccessTokenLifespan() {
        return accessTokenLifespan;
    }

    public void setAccessTokenLifespan(final Integer accessTokenLifespan) {
        this.accessTokenLifespan = accessTokenLifespan;
    }

    public Integer getRefreshTokenLifespan() {
        return refreshTokenLifespan;
    }

    public void setRefreshTokenLifespan(final Integer refreshTokenLifespan) {
        this.refreshTokenLifespan = refreshTokenLifespan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Set<SentryCubeUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SentryCubeUser> users) {
        this.users = users;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(final Set<Client> clients) {
        this.clients = clients;
    }

    public Set<RealmRole> getRealmRoles() {
        return realmRoles;
    }

    public void setRealmRoles(final Set<RealmRole> realmRoles) {
        this.realmRoles = realmRoles;
    }
}
