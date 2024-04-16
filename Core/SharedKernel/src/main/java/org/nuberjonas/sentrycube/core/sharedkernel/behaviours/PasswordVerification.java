package org.nuberjonas.sentrycube.core.sharedkernel.behaviours;

public interface PasswordVerification extends PasswordEncryption {

     default boolean verify(String passwordHash, String providedPassword, String salt){
         var providedPasswordHash = hash(providedPassword, salt);
         return providedPasswordHash.equals(passwordHash);
     }
}
