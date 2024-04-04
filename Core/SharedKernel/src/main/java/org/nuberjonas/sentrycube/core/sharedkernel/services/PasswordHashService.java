package org.nuberjonas.sentrycube.core.sharedkernel.services;

import org.nuberjonas.sentrycube.core.sharedkernel.exceptions.PasswordHashException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public final class PasswordHashService {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    private PasswordHashService(){ }

    public static String generateSalt(){
        var random = new SecureRandom();
        var saltBytes = new byte[32];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public static String hash(String passwordToHash, String salt){
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

    public static boolean verifyPassword(String passwordHash, String providedPassword, String salt){
        var providedPasswordHash = hash(providedPassword, salt);
        return providedPasswordHash.equals(passwordHash);
    }
}
