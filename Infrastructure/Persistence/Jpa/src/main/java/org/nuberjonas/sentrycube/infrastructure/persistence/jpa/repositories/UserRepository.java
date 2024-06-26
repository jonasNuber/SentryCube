package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories;


import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables.SentryCubeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<SentryCubeUser, UUID> {

    @Query("SELECT u FROM SentryCubeUser u WHERE u.username = :username AND u.realm.realmName = :realmName")
    Optional<SentryCubeUser> findByUsernameAndRealmName(@Param("username") String username, @Param("realmName") String realmName);
}
