package org.nuberjonas.sentrycube.core.usermanagement.application;

import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;
import org.nuberjonas.sentrycube.core.usermanagement.domain.entities.User;

public interface UserManagementRepository {

    User findUserById(UserId userId);
}
