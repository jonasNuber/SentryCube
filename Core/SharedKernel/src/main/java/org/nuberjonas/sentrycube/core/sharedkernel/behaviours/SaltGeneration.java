package org.nuberjonas.sentrycube.core.sharedkernel.behaviours;

import java.security.SecureRandom;
import java.util.Base64;

public interface SaltGeneration {
    default String generateSalt(){
        var random = new SecureRandom();
        var saltBytes = new byte[32];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
}
