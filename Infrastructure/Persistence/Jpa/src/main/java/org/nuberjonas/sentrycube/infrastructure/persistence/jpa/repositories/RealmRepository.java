package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories;

import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables.Realm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealmRepository extends JpaRepository<Realm, String> {
}
