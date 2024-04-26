package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;

public record Email(String providedEmail, String persistedEmail) {

    public boolean isValid(){
        return StringUtils.equalsIgnoreCase(providedEmail, persistedEmail);
    }

    public boolean equalsUsername(UserName userName){
        return StringUtils.equalsIgnoreCase(providedEmail, userName.providedUsername());
    }
}
