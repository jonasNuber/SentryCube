package org.nuberjonas.sentrycube.userinterface.rest.api;


import org.nuberjonas.sentrycube.core.auth.application.AuthenticationService;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.ClientNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.RealmNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.UserNotFoundException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.DisabledException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.GrantTypeUnsuportedException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.InvalidCredentialsException;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/oauth")
public class UserAuthenticationController {

    private AuthenticationService service;

    @Autowired
    public UserAuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestParam String grant_type,
                                   @RequestParam(required = false) String username,
                                   @RequestParam(required = false) String password,
                                   @RequestParam String client_id,
                                   @RequestParam String client_secret) {

        try {
            return ResponseEntity.ok(service.authenticateRessourceOwner(
                    new AuthenticationRequest(username, password, client_id, client_secret, null, null, grant_type)));
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DisabledException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (GrantTypeUnsuportedException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (ClientNotFoundException | RealmNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
