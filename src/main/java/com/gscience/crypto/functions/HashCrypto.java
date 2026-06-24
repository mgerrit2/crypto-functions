package com.gscience.crypto.functions;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for securely hashing and verifying passwords using BCrypt.
 * This class is a common component in a Spring Boot application's security layer.
 */
public class HashCrypto {


    // The BCrypt library is used directly, so we don't need to instantiate this class.
    private HashCrypto() {
        // Prevent instantiation
    }

    /**
     * Hashes a plain text password using BCrypt.
     *
     * @param plainPassword The plain text password to be hashed.
     * @return A securely hashed password string.
     */
    public static String hashPassword(String plainPassword) {
        // 'gensalt' generates a unique salt for each hash.
        // The default log rounds is 10, which is a good starting point.
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(plainPassword, salt);
        return hashedPassword;
    }

    /**
     * Verifies a plain text password against a stored hashed password.
     *
     * @param plainPassword The plain text password entered by the user.
     * @param storedHashedPassword The hashed password retrieved from the database.
     * @return true if the passwords match, false otherwise.
     */
    public static boolean verifyPassword(String plainPassword, String storedHashedPassword) {

        try {
            if(StringUtils.isEmpty(storedHashedPassword)) return false;

            // This method automatically extracts the salt from the hashed password
            // and uses it to hash the plain password for comparison.
            return BCrypt.checkpw(plainPassword, storedHashedPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


}
