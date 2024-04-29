package org.nuberjonas.sentrycube.intrastructure.persistence.jpa.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;


@Entity
public class ClientRole {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID clientRoleId;

    @Column(unique = true)
    private String roleName;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", unique = true)
    private Client client;

    @OneToMany(mappedBy = "clientRole")
    private Set<UserClientRole> userClientRoles;

    public UUID getClientRoleId() {
        return clientRoleId;
    }

    public void setClientRoleId(final UUID clientRoleId) {
        this.clientRoleId = clientRoleId;
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

    public Client getClient() {
        return client;
    }

    public void setClient(final Client client) {
        this.client = client;
    }

    public Set<UserClientRole> getUserClientRoles() {
        return userClientRoles;
    }

    public void setUserClientRoles(final Set<UserClientRole> userClientRoles) {
        this.userClientRoles = userClientRoles;
    }

}
