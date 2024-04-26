package org.nuberjonas.sentrycube.userinterface.rest.jpa.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
public class Address {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID addressId;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false, length = 100)
    private String postalCode;

    @Column
    private String state;

    @Column
    private String city;

    @Column
    private String streetName;

    @Column(length = 50)
    private String streetNumber;

    @OneToOne(mappedBy = "address")
    private PersonalData personalData;

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(final UUID addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(final String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(final String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(final PersonalData personalData) {
        this.personalData = personalData;
    }
}
