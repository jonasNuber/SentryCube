package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories;

import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

    @Query("SELECT t FROM Token t WHERE t.session.sessionId = :sessionId")
    List<Token> findTokensBySessionId(@Param("sessionId") UUID sessionId);
}
