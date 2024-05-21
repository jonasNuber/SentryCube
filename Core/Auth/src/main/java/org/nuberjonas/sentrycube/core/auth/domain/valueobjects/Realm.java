package org.nuberjonas.sentrycube.core.auth.domain.valueobjects;

import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.RealmName;

public record Realm(RealmName realmName, Boolean enabled, Boolean emailLogin, Integer accessTokenLifeSpan, Integer refreshTokenLifespan) {
    public boolean disabled(){
        return !enabled();
    }
}
