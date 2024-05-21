package org.nuberjonas.sentrycube.userinterface.rest.api;

import org.nuberjonas.sentrycube.core.auth.application.exceptions.ClientNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.RealmNotFoundException;
import org.nuberjonas.sentrycube.core.auth.application.exceptions.UserNotFoundException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.DisabledException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.GrantTypeUnsuportedException;
import org.nuberjonas.sentrycube.core.auth.domain.exceptions.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleCredentials(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleDisabled(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(GrantTypeUnsuportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleGrantType(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ClientNotFoundException.class, RealmNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFound(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
