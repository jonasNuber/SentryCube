package org.nuberjonas.sentrycube.core.auth.application.repositories;

import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.Realm;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.RealmName;

import java.util.Optional;

public interface AuthRealmRepository {

    Optional<Realm> findByName(RealmName realmName);
}
