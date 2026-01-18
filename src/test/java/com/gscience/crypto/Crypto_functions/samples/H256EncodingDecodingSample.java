package com.gscience.crypto.Crypto_functions.samples;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class H256EncodingDecodingSample {

    // A secure, Base64-encoded key for HS256 (must be 32+ bytes when decoded)
    private static final String SECRET = "QkFTZTY0RW5jb2RlZFNlY3JldEtleUZvcjM0Qnl0ZXNTaWduaW5n";

    // 10 minutes expiration time
    private static final long EXPIRATION_MS = TimeUnit.MINUTES.toMillis(10);

    @Test
    void testH256EncodingDecoding() {

        // 1. Get the Signing Key
        SecretKey signingKey = getSigningKey();
        System.out.println("--- 1. Signing Key Prepared ---");

        // 2. Create the Token
        String jwtToken = createToken(signingKey, "user123", "Admin");
        System.out.println("\n--- 2. Token Created ---");
        System.out.println("JWT: " + jwtToken);

        // 3. Parse and Verify the Token
        System.out.println("\n--- 3. Token Verification & Parsing ---");
        parseToken(signingKey, jwtToken);
    }

    /**
     * Converts the Base64 String secret into a secure SecretKey object.
     */
    private static SecretKey getSigningKey() {
        // Decode the Base64 String
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        // Create the HMAC key
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Creates and signs a JWT using the HS256 algorithm.
     */
    private static String createToken(SecretKey key, String subject, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(subject) // The user identifier
                .claim("role", role) // A custom claim
                .setSubject("mgerrit2")
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256) // HS256 Signing
                .compact();
    }

    /**
     * Parses the JWT, verifies the signature using the same key, and extracts claims.
     */
    private static void parseToken(SecretKey key, String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key) // Verification using the secret key
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            System.out.println("  ✔ Signature Validated!");
            System.out.println("  - Subject (Username): " + claims.getSubject());
            System.out.println("  - Custom Claim (Role): " + claims.get("role", String.class));
            System.out.println("  - Expiration Date: " + claims.getExpiration());

        } catch (Exception e) {
            // This catches expired tokens, invalid signatures, malformed JWTs, etc.
            System.err.println("  ❌ Token Validation Failed: " + e.getMessage());
        }
    }
}
