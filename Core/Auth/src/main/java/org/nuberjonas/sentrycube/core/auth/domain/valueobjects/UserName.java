package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;

public record UserName(String name) {

    public boolean isValid(UserName providedUsername){
        return StringUtils.equalsIgnoreCase(providedUsername.name, name);
    }
}
