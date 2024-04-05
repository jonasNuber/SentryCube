package org.nuberjonas.sentrycube.core.usermanagement.domain.entities;

import org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects.*;

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

    private User(Builder builder){
        userId = builder.userId;
        userName = builder.userName;
        email = builder.email;
        password = builder.password;
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

    public static class Builder{

        private final UserId userId;
        private final UserName userName;
        private final Email email;
        private final Password password;

        public Builder(String name, String emailAddress, String newPassword){
            Objects.requireNonNull(name, "No username provided.");
            Objects.requireNonNull(emailAddress, "No email address provided.");
            Objects.requireNonNull(newPassword, "No password provided.");

            userId = new UserId();
            userName = new UserName(name);
            email = new Email(emailAddress);
            password = new Password(newPassword);
        }

        public User build(){
            return new User(this);
        }
    }
}
