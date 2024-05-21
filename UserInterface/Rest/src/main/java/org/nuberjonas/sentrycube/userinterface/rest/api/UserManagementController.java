package org.nuberjonas.sentrycube.userinterface.rest.api;

import org.nuberjonas.sentrycube.core.usermanagement.application.requests.UserCreationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserManagementController {

    @PostMapping("/user")
    public void createUser(@RequestBody UserCreationRequest creationRequest){

    }
    
}
