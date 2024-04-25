package org.nuberjonas.sentrycube.intrastructure.persistence.jpa.tables;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class ClientRole {

    @Id
    @Column(nullable = false, updatable = false)
    private String roleName;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "client")
    private Set<UserClientRole> userClientRoles;

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
