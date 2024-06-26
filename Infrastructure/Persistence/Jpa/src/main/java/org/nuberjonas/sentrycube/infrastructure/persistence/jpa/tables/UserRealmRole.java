package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
public class UserRealmRole {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID userRealmRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private SentryCubeUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realm_role_id")
    private RealmRole realmRole;

    public UUID getUserRealmRoleId() {
        return userRealmRoleId;
    }

    public void setUserRealmRoleId(final UUID userRealmRoleId) {
        this.userRealmRoleId = userRealmRoleId;
    }

    public SentryCubeUser getUser() {
        return user;
    }

    public void setUser(final SentryCubeUser user) {
        this.user = user;
    }

    public RealmRole getRealmRole() {
        return realmRole;
    }

    public void setRealmRole(final RealmRole realmRole) {
        this.realmRole = realmRole;
    }

}
