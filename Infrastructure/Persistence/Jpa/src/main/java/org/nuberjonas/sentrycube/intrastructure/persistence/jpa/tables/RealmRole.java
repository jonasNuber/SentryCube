package org.nuberjonas.sentrycube.intrastructure.persistence.jpa.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;


@Entity
public class RealmRole {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID realmRoleId;

    @Column(nullable = false, updatable = false)
    private String roleName;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realm_name")
    private Realm realm;

    @OneToMany(mappedBy = "realmRole")
    private Set<UserRealmRole> userRealmRoles;

    public UUID getRealmRoleId() {
        return realmRoleId;
    }

    public void setRealmRoleId(UUID realmRoleId) {
        this.realmRoleId = realmRoleId;
    }

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

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(final Realm realm) {
        this.realm = realm;
    }

    public Set<UserRealmRole> getUserRealmRoles() {
        return userRealmRoles;
    }

    public void setUserRealmRoles(final Set<UserRealmRole> userRealmRoles) {
        this.userRealmRoles = userRealmRoles;
    }

}
