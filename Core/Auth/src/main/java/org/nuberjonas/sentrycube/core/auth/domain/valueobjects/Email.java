package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;

public record Email(String address) {

    public boolean isValid(Email providedEmail){
        return StringUtils.equalsIgnoreCase(providedEmail.address, address);
    }

    public boolean equalsUsername(UserName userName){
        return StringUtils.equalsIgnoreCase(address, userName.name());
    }
}
