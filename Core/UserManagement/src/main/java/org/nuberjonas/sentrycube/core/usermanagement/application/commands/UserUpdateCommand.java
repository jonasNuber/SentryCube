package org.nuberjonas.sentrycube.core.usermanagement.application.commands;

import org.nuberjonas.sentrycube.core.usermanagement.domain.entities.User;

public interface UserUpdateCommand {

     void execute(User user);
}
