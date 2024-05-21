package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.auth;

import org.nuberjonas.sentrycube.core.auth.application.repositories.AuthRealmRepository;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.Realm;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.RealmName;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.RealmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaAuthRealmRepository implements AuthRealmRepository {

    private RealmRepository realmRepository;

    @Autowired
    public JpaAuthRealmRepository(RealmRepository realmRepository) {
        this.realmRepository = realmRepository;
    }

    @Override
    public Optional<Realm> findByName(RealmName realmName) {
        var persistedRealm = realmRepository.findById(realmName.name());

        return persistedRealm.flatMap(realm -> Optional.of(
                new Realm(realmName,
                        realm.getEnabled(),
                        realm.getEmailLogin(),
                        realm.getAccessTokenLifespan(),
                        realm.getRefreshTokenLifespan())
        ));
    }
}
