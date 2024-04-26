package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;

public record UserName(String providedUsername, String persistedUsername) {

    public boolean isValid(){
        return StringUtils.equalsIgnoreCase(providedUsername, persistedUsername);
    }
}
