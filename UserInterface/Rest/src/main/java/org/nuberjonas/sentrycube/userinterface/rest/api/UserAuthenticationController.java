package org.nuberjonas.sentrycube.userinterface.rest.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class UserAuthenticationController {

    @PostMapping(path = "/{realmName}/authenticate")
    public void authenticate(@PathVariable String realmName){

    }
}
