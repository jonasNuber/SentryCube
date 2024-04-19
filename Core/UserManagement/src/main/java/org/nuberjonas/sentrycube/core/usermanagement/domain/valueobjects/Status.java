package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended"),
    UNVERIFIED("Unverified"),
    DELETED("Deleted");

    private final String value;

    private Status(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
