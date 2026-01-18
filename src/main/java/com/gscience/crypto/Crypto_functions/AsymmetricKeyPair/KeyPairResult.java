package com.gscience.crypto.Crypto_functions.AsymmetricKeyPair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.security.PublicKey;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class KeyPairResult {
    private PrivateKey privateKey;
    private PublicKey publicKey;

}
