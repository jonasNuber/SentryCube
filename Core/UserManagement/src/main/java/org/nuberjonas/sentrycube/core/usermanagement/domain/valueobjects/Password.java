package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;
import org.nuberjonas.sentrycube.core.sharedkernel.services.PasswordHashService;

public final class Password {

    private final String passwordHash;
    private final String salt;

    public Password(String passwordHash, String salt) {
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public Password(String newPassword){
        if(StringUtils.isEmpty(newPassword)){
            throw new IllegalArgumentException("Password must not be empty.");
        }

        this.salt = PasswordHashService.generateSalt();
        this.passwordHash = PasswordHashService.hash(newPassword, salt);
    }

    public Password change(String newPassword){
        return new Password(newPassword);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }
}
