package org.nuberjonas.sentrycube.intrastructure.persistence.jpa.tables;

import jakarta.persistence.*;


@Entity
public class UserRealmRole {

    @Id
    @Column(nullable = false, updatable = false)
    private String roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realm_name_id")
    private RealmRole realmRole;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public RealmRole getRealmRole() {
        return realmRole;
    }

    public void setRealmRole(final RealmRole realmRole) {
        this.realmRole = realmRole;
    }

}
