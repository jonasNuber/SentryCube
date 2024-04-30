package org.nuberjonas.sentrycube.userinterface.rest.persistence.repositories;

import org.nuberjonas.sentrycube.userinterface.rest.persistence.tables.SentryCubeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<SentryCubeUser, UUID> {
}
