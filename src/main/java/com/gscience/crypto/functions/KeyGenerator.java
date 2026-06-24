package com.gscience.crypto.functions;

import io.jsonwebtoken.Jwts;


import javax.crypto.SecretKey;
import java.util.Base64;

/**
 * Utility class to generate secure cryptographic keys for JWT signing.
 * Uses JJWT 0.12.x+ modern API.
 */
public class KeyGenerator {

    // The BCrypt library is used directly, so we don't need to instantiate this class.
    private KeyGenerator() {
        // Prevent instantiation
    }

    /**
     * Utility class to generate secure cryptographic keys for JWT signing.
     * Uses JJWT 0.12.x+ modern API.
     */
    public static String createKey(){
        SecretKey key = Jwts.SIG.HS256.key().build();

        // Encode it to Base64 so you can store it in your application.properties or .env file
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }



}
