package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;

public record UserName(String name) {

    public UserName(String name) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Username must not be empty.");
        }

        this.name = name;
    }

    public UserName change(String newName){
        return new UserName(newName);
    }
}
