package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;

public final class Name {

    private final String firstName;
    private final String[] middleNames;
    private final String lastName;

    public Name(String firstName, String lastName, String... middleNames) {
        if(StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)){
            throw new IllegalArgumentException("First and last names can not be empty");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.middleNames = middleNames;
    }

    public Name change(String firstName, String lastName, String... middleNames){
        return new Name(firstName == null ? this.firstName : firstName,
                        lastName == null ? this.lastName : lastName,
                        middleNames);
    }

    public String getFirstName() {
        return firstName;
    }

    public String[] getMiddleNames() {
        return middleNames;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        var builder = new StringBuilder();

        builder.append(firstName).append(" ");

        for(var middleName : middleNames){
            builder.append(middleName).append(" ");
        }

        builder.append(lastName);

        return builder.toString().trim();
    }
}
