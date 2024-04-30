package org.nuberjonas.sentrycube.userinterface.rest.persistence.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
public class RedirectUri {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID redirectUriId;

    @Column(nullable = false, length = 510)
    private String uri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public UUID getRedirectUriId() {
        return redirectUriId;
    }

    public void setRedirectUriId(final UUID redirectUriId) {
        this.redirectUriId = redirectUriId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(final Client client) {
        this.client = client;
    }

}
