package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;

public record Name(String firstName, String lastName) {

    public Name(String firstName, String lastName) {
        if(StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)){
            throw new IllegalArgumentException("First and last names can not be empty");
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name change(String firstName, String lastName){
        return new Name(firstName == null ? this.firstName : firstName,
                        lastName == null ? this.lastName : lastName);
    }

    public String fullName(){
        var builder = new StringBuilder();

        return builder.append(firstName).append(" ").append(lastName).toString().trim();
    }
}
