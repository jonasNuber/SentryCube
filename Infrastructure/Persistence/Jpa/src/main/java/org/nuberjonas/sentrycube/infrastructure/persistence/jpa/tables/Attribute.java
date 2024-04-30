package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
public class Attribute {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID attributeId;

    @Column(nullable = false)
    private String name;

    @Column
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private SentryCubeUser user;

    public UUID getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(final UUID attributeId) {
        this.attributeId = attributeId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public SentryCubeUser getUser() {
        return user;
    }

    public void setUser(final SentryCubeUser user) {
        this.user = user;
    }

}
