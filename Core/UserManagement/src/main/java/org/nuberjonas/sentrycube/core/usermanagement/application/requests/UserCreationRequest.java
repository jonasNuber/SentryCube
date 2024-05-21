package org.nuberjonas.sentrycube.core.usermanagement.application.requests;

public record UserCreationRequest(String username, String email, String password, String name) {
}
