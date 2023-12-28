package com.nursery.management.config;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {
    public static String createSecret() {
        int length = 32;

        byte[] secretBytes = new byte[length];
        new SecureRandom().nextBytes(secretBytes);
        
        String jwtSecret = Base64.getEncoder().encodeToString(secretBytes);

        System.out.println("Generated JWT Secret: " + jwtSecret);
        return jwtSecret;
    }
}