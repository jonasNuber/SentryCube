package org.nuberjonas.sentrycube.userinterface.rest.persistence.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
public class UserClientRole {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID userClientRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private SentryCubeUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_role_id")
    private ClientRole clientRole;

    public UUID getUserClientRoleId() {
        return userClientRoleId;
    }

    public void setUserClientRoleId(final UUID userClientRoleId) {
        this.userClientRoleId = userClientRoleId;
    }

    public SentryCubeUser getUser() {
        return user;
    }

    public void setUser(final SentryCubeUser user) {
        this.user = user;
    }

    public ClientRole getClientRole() {
        return clientRole;
    }

    public void setClientRole(final ClientRole clientRole) {
        this.clientRole = clientRole;
    }

}
