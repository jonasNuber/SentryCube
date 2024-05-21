package org.nuberjonas.sentrycube.core.auth.application.repositories;

import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.User;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.UserName;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.RealmName;

import java.util.Optional;

public interface AuthUserRepository {

    Optional<User> findByUsernameAndRealmname(UserName username, RealmName realmName);
}
