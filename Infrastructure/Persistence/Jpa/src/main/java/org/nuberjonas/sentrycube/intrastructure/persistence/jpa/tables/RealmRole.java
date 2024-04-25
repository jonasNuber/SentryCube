package org.nuberjonas.sentrycube.intrastructure.persistence.jpa.tables;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class RealmRole {

    @Id
    @Column(nullable = false, updatable = false)
    private String roleName;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realm_name_id")
    private Realm realmName;

    @OneToMany(mappedBy = "realmName")
    private Set<UserRealmRole> userRealmRoles;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Realm getRealmName() {
        return realmName;
    }

    public void setRealmName(final Realm realmName) {
        this.realmName = realmName;
    }

    public Set<UserRealmRole> getUserRealmRoles() {
        return userRealmRoles;
    }

    public void setUserRealmRoles(final Set<UserRealmRole> userRealmRoles) {
        this.userRealmRoles = userRealmRoles;
    }

}
