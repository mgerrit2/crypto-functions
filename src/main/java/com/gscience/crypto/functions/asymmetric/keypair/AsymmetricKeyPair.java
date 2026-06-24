package com.gscience.crypto.functions.asymmetric.keypair;

import java.security.*;
import java.util.Base64;

public class AsymmetricKeyPair {

    private KeyPairResult keyPairResult;

    public KeyPairResult generateKey() throws NoSuchAlgorithmException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, new SecureRandom());

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        return KeyPairResult.builder()
                .privateKey( keyPair.getPrivate())
                .publicKey(keyPair.getPublic())
                .build();
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
