package com.gscience.crypto.Crypto_functions.AsymmetricKeyPair;

import java.security.*;
import java.util.Base64;

public class AsymmetricKeyPair {

    private KeyPairResult keyPairResult;

    public KeyPairResult generateKey() throws NoSuchAlgorithmException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, new SecureRandom());

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        var keyPairResult= KeyPairResult.builder()
                .privateKey( keyPair.getPrivate())
                .publicKey(keyPair.getPublic())
                .build();

        return keyPairResult;
    }

    public KeyPairStringResult toKeyPairString(){

        byte[] pubKeyBytes = keyPairResult.getPublicKey().getEncoded();
        byte[] privKeyBytes = keyPairResult.getPrivateKey().getEncoded();

        return KeyPairStringResult.builder()
                .publicKey(Base64.getEncoder().encodeToString(pubKeyBytes))
                .privateKey(Base64.getEncoder().encodeToString(privKeyBytes))
                .build();
    }

}
