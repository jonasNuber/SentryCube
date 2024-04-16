package org.nuberjonas.sentrycube.core.usermanagement.application;

import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.UserId;
import org.nuberjonas.sentrycube.core.usermanagement.application.commands.UserUpdateCommand;

public class UserManagementService {
    private UserManagementRepository userManagementRepository;

    public UserManagementService(UserManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }

    public void createUser(){

    }

    public void updateUser(UserId userId, UserUpdateCommand... updates){
        var user = userManagementRepository.findUserById(userId);

        for(var update : updates){
            update.execute(user);
        }
    }

    public void deleteUser(){
        
    }
}
