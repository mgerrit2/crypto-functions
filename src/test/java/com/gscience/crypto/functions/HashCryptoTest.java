package com.gscience.crypto.functions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the HashCrypto utility class using JUnit 5.
 */
@DisplayName("HashCrypto Utility Tests")
class HashCryptoTest {

    //private static final String PLAIN_PASSWORD = "MySecurePassword123!";
    private static final String PLAIN_PASSWORD = "poseidon";
    private static final String WRONG_PASSWORD = "AnIncorrectPassword";

    // --- Test: Hashing ---

    @Test
    @DisplayName("HashCrypto generates a valid BCrypt hash")
    void testHashPassword_is_valid_bcrypt_hash() {
        // ACT
        String hashedPassword = HashCrypto.hashPassword(PLAIN_PASSWORD);

        // ASSERT
        // 1. The hash should not be null or empty
        assertNotNull(hashedPassword, "The hashed password should not be null.");
        assertFalse(hashedPassword.isEmpty(), "The hashed password should not be empty.");

        // 2. The hash should be a valid BCrypt hash (starts with $2a$, $2b$, or $2y$)
        assertTrue(hashedPassword.matches("^\\$2[aby]\\$.{56}$"),
                "The hashed password should match the BCrypt format.");
    }

    @Test
    @DisplayName("HashCrypto generates a different hash for the same input (unique salt)")
    void testHashPassword_is_unique_due_to_salt() {
        // ACT
        String hash1 = HashCrypto.hashPassword(PLAIN_PASSWORD);
        String hash2 = HashCrypto.hashPassword(PLAIN_PASSWORD);

        // ASSERT
        // BCrypt uses a unique salt for every hash, so two hashes of the same
        // plaintext should be different, but both should be verifiable.
        assertNotEquals(hash1, hash2, "Two hashes of the same password should be different.");

        // Double-check both are still verifiable
        assertTrue(HashCrypto.verifyPassword(PLAIN_PASSWORD, hash1), "Hash 1 should still verify.");
        assertTrue(HashCrypto.verifyPassword(PLAIN_PASSWORD, hash2), "Hash 2 should still verify.");
    }

    // --- Test: Verification ---

    @Test
    @DisplayName("verifyPassword returns true for a correct password")
    void testVerifyPassword_correct_password() {
        // ARRANGE
        String hashedPassword = HashCrypto.hashPassword(PLAIN_PASSWORD);

        // ACT & ASSERT
        assertTrue(HashCrypto.verifyPassword(PLAIN_PASSWORD, hashedPassword),
                "Verification should pass for the correct password.");
    }

    @Test
    @DisplayName("verifyPassword returns false for an incorrect password")
    void testVerifyPassword_incorrect_password() {
        // ARRANGE
        String hashedPassword = HashCrypto.hashPassword(PLAIN_PASSWORD);

        // ACT & ASSERT
        assertFalse(HashCrypto.verifyPassword(WRONG_PASSWORD, hashedPassword),
                "Verification should fail for an incorrect password.");
    }

    @Test
    @DisplayName("verifyPassword returns false when comparing with null or empty hash")
    void testVerifyPassword_with_invalid_hash() {


        // ACT & ASSERT
        // Check against null
        assertFalse(HashCrypto.verifyPassword(PLAIN_PASSWORD, null),
                "Verification should fail when stored hash is null.");

        // Check against empty string
        assertFalse(HashCrypto.verifyPassword(PLAIN_PASSWORD, ""),
                "Verification should fail when stored hash is empty.");

        // Check against non-BCrypt string
        assertFalse(HashCrypto.verifyPassword(PLAIN_PASSWORD, "notabCrypthash"),
                "Verification should fail when stored hash is invalid format.");
    }
}