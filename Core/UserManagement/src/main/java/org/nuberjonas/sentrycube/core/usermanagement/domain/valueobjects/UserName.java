package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;

public final class UserName {

    private final String name;

    public UserName(String name) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Name must not be empty.");
        }

        this.name = name;
    }

    public UserName change(String newName){
        return new UserName(newName);
    }

    public String get(){
        return name;
    }
}
