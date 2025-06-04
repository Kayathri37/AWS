package com.AWS.Figma.util;
import com.AWS.Figma.Entity.Register;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import io.jsonwebtoken.Jwts;

@Component
public class JwtToken {

    private static final String SECRET_KEY = "This_is_a_secure_256bit_secret_key!!!";
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String createToken(Register register) {



        return Jwts.builder()
                .setIssuer(String.valueOf(register.getEmailId()))
                .signWith(key)
                .compact();
    }

    public Claims verifyToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("JWT token is missing or empty");
        }

        return Jwts.parser()
                .setSigningKey(key) // your secret key
                .parseClaimsJws(token)
                .getBody();
    }
}
