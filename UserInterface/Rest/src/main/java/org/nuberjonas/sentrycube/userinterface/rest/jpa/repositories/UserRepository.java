package org.nuberjonas.sentrycube.userinterface.rest.jpa.repositories;

import org.nuberjonas.sentrycube.userinterface.rest.jpa.tables.SentryCubeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<SentryCubeUser, UUID> {
}
