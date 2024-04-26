package org.nuberjonas.sentrycube.intrastructure.persistence.jpa.repositories;

import org.nuberjonas.sentrycube.intrastructure.persistence.jpa.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
