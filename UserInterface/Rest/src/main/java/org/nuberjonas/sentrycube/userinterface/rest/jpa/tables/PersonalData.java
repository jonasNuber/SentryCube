package org.nuberjonas.sentrycube.userinterface.rest.jpa.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.nuberjonas.sentrycube.userinterface.rest.jpa.data.Gender;

import java.time.LocalDate;
import java.util.UUID;


@Entity
public class PersonalData {

    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID personalDataId;

    @Column
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 120)
    private String phoneNumber;

    @Column(columnDefinition = "text")
    private byte[] profilePicData;

    @OneToOne(mappedBy = "personalData")
    private SentryCubeUser user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_id")
    private Name name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public UUID getPersonalDataId() {
        return personalDataId;
    }

    public void setPersonalDataId(final UUID personalDataId) {
        this.personalDataId = personalDataId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getProfilePicData() {
        return profilePicData;
    }

    public void setProfilePicData(final byte[] profilePicData) {
        this.profilePicData = profilePicData;
    }

    public SentryCubeUser getUser() {
        return user;
    }

    public void setUser(final SentryCubeUser user) {
        this.user = user;
    }

    public Name getName() {
        return name;
    }

    public void setName(final Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

}
