package org.nuberjonas.sentrycube.core.usermanagement.domain.entities;

import org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects.UserName;
import org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects.Email;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;
import org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects.*;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private final UserId userId;

    private UserName userName;
    private Email email;
    private Password password;
    private Name name;
    private Gender gender;
    private DateOfBirth dateOfBirth;
    private Status status;
    private ProfilePicture profilePicture;
    private CreationDate creationDate;

    private User(Builder builder){
        userId = builder.userId;
        userName = builder.userName;
        email = builder.email;
        password = builder.password;
        name = builder.name;
        gender = builder.gender;
        dateOfBirth = builder.dateOfBirth;
        status = builder.status;
        profilePicture = builder.profilePicture;
        creationDate = builder.creationDate;
    }

    public void changeUserName(String newName){
        userName = userName.change(newName);
    }

    public void changeEmail(String newEmailAddress){
        email = email.change(newEmailAddress);
    }

    public void changePassword(String newPassword){
        password = password.change(newPassword);
    }

    public void changeName(String firstName, String lastName){
        name = name.change(firstName, lastName);
    }

    public void changeGender(Gender newGender){
        gender = newGender;
    }

    public static class Builder{

        private final UserId userId;
        private final UserName userName;
        private final Email email;
        private final Password password;
        private final Status status;
        private final CreationDate creationDate;

        private Name name;
        private Gender gender;
        private DateOfBirth dateOfBirth;
        private ProfilePicture profilePicture;

        public Builder(String name, String emailAddress, String newPassword){
            Objects.requireNonNull(name, "No username provided.");
            Objects.requireNonNull(emailAddress, "No email address provided.");
            Objects.requireNonNull(newPassword, "No password provided.");

            userId = new UserId();
            userName = new UserName(name);
            email = new Email(emailAddress);
            password = new Password(newPassword);
            status = Status.UNVERIFIED;
            creationDate = new CreationDate();
        }

        public Builder name(String firstName, String lastName){
            if(firstName != null && lastName != null){
                name = new Name(firstName, lastName);
            }

            return this;
        }

        public Builder gender(Gender gender){
            if(gender != null){
                this.gender = gender;
            }

            return this;
        }

        public Builder gender(String abbreviation){
            if(abbreviation != null){
                gender = Gender.forAbbreviation(abbreviation);
            }

            return this;
        }

        public Builder dateOfBirth(LocalDate date){
            if(date != null){
                dateOfBirth = new DateOfBirth(date);
            }

            return this;
        }

        public Builder profilePicture(byte[] imageData){
            if(imageData != null){
                profilePicture = new ProfilePicture(imageData);
            }

            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
