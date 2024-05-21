package org.nuberjonas.sentrycube.core.auth.application.repositories;

import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.Client;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientId;

import java.util.Optional;

public interface AuthClientRepository {

    Optional<Client> findById(ClientId id);
}
