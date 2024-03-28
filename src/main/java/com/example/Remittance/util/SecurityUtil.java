package com.example.Remittance.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Encrypts (hashes) a text password using BCrypt.
     *
     * @param plainTextPassword the text password to encrypt
     * @return the encrypted (hashed) password
     */
    public static String encryptPassword(String plainTextPassword) {
        return encoder.encode(plainTextPassword);
    }

    /**
     * Verifies if a text password matches the encrypted (hashed) version.
     *
     * @param plainTextPassword the text password
     * @param encryptedPassword the encrypted (hashed) password to compare against
     * @return true if the passwords match, false otherwise
     */
    public static boolean matches(String plainTextPassword, String encryptedPassword) {
        return encoder.matches(plainTextPassword, encryptedPassword);
    }
}
