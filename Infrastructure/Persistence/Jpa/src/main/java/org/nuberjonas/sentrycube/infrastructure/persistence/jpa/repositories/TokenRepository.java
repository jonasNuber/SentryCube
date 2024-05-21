package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories;

import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
}
