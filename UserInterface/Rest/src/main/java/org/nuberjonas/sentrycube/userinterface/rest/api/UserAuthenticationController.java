package org.nuberjonas.sentrycube.userinterface.rest.api;


import org.nuberjonas.sentrycube.userinterface.rest.persistence.repositories.UserRepository;
import org.nuberjonas.sentrycube.userinterface.rest.persistence.tables.SentryCubeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController()
@RequestMapping("/api")
public class UserAuthenticationController {

    private UserRepository repository;

    @Autowired
    public UserAuthenticationController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/{realmName}/authenticate")
    public ResponseEntity<List<SentryCubeUser>> authenticate(@PathVariable(name = "realmName") String realmName, @RequestHeader(HttpHeaders.USER_AGENT) String userAgent){
        return ResponseEntity.of(Optional.of(repository.findAll()));
    }
}
