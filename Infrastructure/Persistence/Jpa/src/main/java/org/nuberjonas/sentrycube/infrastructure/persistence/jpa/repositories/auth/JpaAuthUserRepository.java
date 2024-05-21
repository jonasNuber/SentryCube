package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.auth;

import org.nuberjonas.sentrycube.core.auth.application.repositories.AuthUserRepository;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.*;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.RealmName;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaAuthUserRepository implements AuthUserRepository {

    private UserRepository userRepository;

    @Autowired
    public JpaAuthUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsernameAndRealmname(UserName username, RealmName realmName) {
        var persistenceUser = userRepository.findByUsernameAndRealmName(username.name(), realmName.name());

        return persistenceUser.flatMap(sentryCubeUser -> Optional.of(
                new User(new UserId(sentryCubeUser.getUserId()),
                        new UserCredentials(username, new Email(sentryCubeUser.getEmail()), new Password(sentryCubeUser.getPasswordHash(), sentryCubeUser.getPasswordSalt())),
                        sentryCubeUser.getRealmRoles().stream().map(userRealmRole -> new RealmRole(userRealmRole.getRealmRole().getRoleName())).toList(),
                        sentryCubeUser.getClientRoles().stream().map(userClientRole -> new ClientRole(userClientRole.getClientRole().getRoleName())).toList(),
                        null
                        ))
        );
    }
}
