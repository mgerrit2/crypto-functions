package com.gscience.crypto.functions;

import com.gscience.crypto.functions.asymmetric.keypair.AsymmetricKeyPair;
import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AsymmetricKeyPairSpec {

    @Test
    void createKeyPair() throws NoSuchAlgorithmException {
        // Arrange
        var asymmetricKeyPair = new AsymmetricKeyPair();

        // Act
        var keyPair = asymmetricKeyPair.generateKey();

        // Assert
        assertNotNull(keyPair, "KeyPair mag niet null zijn na generatie");
        assertNotNull(keyPair.getPrivateKey(), "Private key moet aanwezig zijn");
        assertNotNull(keyPair.getPublicKey(), "Public key moet aanwezig zijn");
    }

    @Test
    void getKeyPairString() throws NoSuchAlgorithmException {
        // Arrange
        var asymmetricKeyPair = new AsymmetricKeyPair();
        asymmetricKeyPair.generateKey(); // Zorg dat de sleutel eerst gegenereerd is

        // Act
        var keyPairString = asymmetricKeyPair.toKeyPairString();

        // Assert
        assertNotNull(keyPairString, "De string-representatie mag niet null zijn");

    }
}
