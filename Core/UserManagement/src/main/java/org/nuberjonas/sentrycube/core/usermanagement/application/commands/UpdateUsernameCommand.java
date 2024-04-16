package org.nuberjonas.sentrycube.core.usermanagement.application.commands;

import org.nuberjonas.sentrycube.core.usermanagement.domain.entities.User;

public final class UpdateUsernameCommand implements UserUpdateCommand {

    private final String newUserName;

    public UpdateUsernameCommand(String newUserName) {
        this.newUserName = newUserName;
    }

    @Override
    public void execute(User user) {
        user.changeUserName(newUserName);
    }
}
