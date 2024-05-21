package org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.auth;

import org.nuberjonas.sentrycube.core.auth.application.repositories.AuthClientRepository;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.Client;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.ClientCredentials;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.GrantType;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.Protocol;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.ClientSecret;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.RealmName;
import org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaAuthClientRepository implements AuthClientRepository {

    private ClientRepository clientRepository;

    @Autowired
    public JpaAuthClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> findById(ClientId clientId) {
        var persistenceClient = clientRepository.findById(clientId.id());

        return persistenceClient.flatMap(client -> Optional.of(new Client(
                new ClientCredentials(clientId, new ClientSecret(client.getClientSecret())),
                new RealmName(client.getRealm().getRealmName()),
                Protocol.valueOf(client.getProtocol().name()),
                GrantType.valueOf(client.getGrantType().name()),
                client.getEnabled(),
                null)));
    }
}
