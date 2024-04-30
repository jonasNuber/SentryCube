package org.nuberjonas.sentrycube.userinterface.rest.persistence.repositories;

import org.nuberjonas.sentrycube.userinterface.rest.persistence.tables.SentryCubeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<SentryCubeUser, UUID> {
}
