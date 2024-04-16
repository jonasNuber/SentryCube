package org.nuberjonas.sentrycube.core.sharedkernel.behaviours;

import org.nuberjonas.sentrycube.core.sharedkernel.exceptions.PasswordHashException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public interface PasswordEncryption {
    int ITERATIONS = 65536;
    int KEY_LENGTH = 256;

    default String hash(String passwordToHash, String salt){
        var passwordChars = passwordToHash.toCharArray();
        var saltBytes = salt.getBytes();

        var spec = new PBEKeySpec(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);

        try {
            var skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            var hashedBytes = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new PasswordHashException("Password could not be hashed", e);
        }
    }
}
