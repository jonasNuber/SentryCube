package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories;

import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Query("SELECT s FROM Session s WHERE s.user.userId = :userId")
    List<Session> findSessionsByUserId(@Param("userId") UUID userId);
}
