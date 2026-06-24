package com.gscience.crypto.functions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;

class KeyGeneratorTest {

    @Test
    @DisplayName("Should generate a valid, non-empty Base64 encoded secret key")
    void shouldGenerateValidBase64Key() {
        // Act
        String base64Key = KeyGenerator.createKey();

        // Assert
        assertNotNull(base64Key, "The generated key should not be null");
        assertFalse(base64Key.isBlank(), "The generated key should not be blank");

        // Verify it can be successfully decoded back from Base64
        assertDoesNotThrow(() -> Base64.getDecoder().decode(base64Key),
                "The generated key must be a valid Base64 encoded string");
    }

    @Test
    @DisplayName("Should generate a key with a minimum of 256 bits (32 bytes) for HS256 compliance")
    void shouldMeetMinimumLengthRequirements() {
        // Act
        String base64Key = KeyGenerator.createKey();
        byte[] decodedKeyBytes = Base64.getDecoder().decode(base64Key);

        // Assert
        // HMAC-SHA256 requires a key size of at least 256 bits (32 bytes)
        assertTrue(decodedKeyBytes.length >= 32,
                "The decoded key length must be at least 32 bytes (256 bits) to be secure for HS256");
    }

    @Test
    @DisplayName("Should generate a unique key on every invocation")
    void shouldGenerateUniqueKeys() {
        // Act
        String firstKey = KeyGenerator.createKey();
        String secondKey = KeyGenerator.createKey();

        // Assert
        assertNotEquals(firstKey, secondKey,
                "Subsequent calls to createKey() should produce unique cryptographic keys");
    }
}