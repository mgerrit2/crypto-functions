package com.gscience.crypto.Crypto_functions;

import com.gscience.crypto.Crypto_functions.AsymmetricKeyPair.AsymmetricKeyPair;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class AsymmetricKeyPairSpec {

    @Test
    void createKeyPair() throws NoSuchAlgorithmException {

        var asymmetricKeyPair = new AsymmetricKeyPair();
        var keyPair = asymmetricKeyPair.generateKey();

    }

    @Test
    void getKeyPairString() throws NoSuchAlgorithmException {

        var asymmetricKeyPair = new AsymmetricKeyPair();
        var keyPair = asymmetricKeyPair.generateKey();

        var keiPairString =asymmetricKeyPair.toKeyPairString();


    }
}
