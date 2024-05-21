package org.nuberjonas.sentrycube.userinterface.rest.api;


import jakarta.servlet.http.HttpServletRequest;
import org.nuberjonas.sentrycube.core.auth.application.AuthenticationService;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.ClientNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.RealmNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.UserNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.responses.TokenResponse;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.DisabledException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.GrantTypeUnsuportedException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.InvalidCredentialsException;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/oauth")
public class UserAuthenticationController {

    private AuthenticationService service;

    @Autowired
    public UserAuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> token(
            HttpServletRequest request,
            @RequestHeader(value = "User-Agent") String userAgent,
            @RequestHeader("grant_type") String grantType,
            @RequestHeader(value = "username", required = false) String username,
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader("client_id") String clientId,
            @RequestHeader("client_secret") String clientSecret)
            throws UserNotFoundException, ClientNotFoundException, InvalidCredentialsException, DisabledException, GrantTypeUnsuportedException, RealmNotFoundException {

        AuthenticationRequest authRequest = new AuthenticationRequest(username, password, clientId, clientSecret, userAgent, request.getRemoteAddr(), grantType);
        TokenResponse tokenResponse = service.authenticateResourceOwner(authRequest);
        return ResponseEntity.ok(tokenResponse);
    }
}
