package com.POS.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "this-is-a-demo-secret-key-for-jwt-please-change-it-1234567890";
    private final long EXPIRATION_TIME_MS = 1000 * 60 * 60;              // 1 hour (rememberMe = false)
    private final long REMEMBER_ME_EXPIRATION_MS = 1000L * 60 * 60 * 24 * 30; // 30 days (rememberMe = true)

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Purana method — jahan kahin bhi call ho raha hai wahan bina badlaav ke chalega
    public String generateToken(String mobileNumber) {
        return generateToken(mobileNumber, false);
    }

    // Naya overload — rememberMe ke hisaab se expiry decide karta hai
    public String generateToken(String mobileNumber, boolean rememberMe) {
        Date now = new Date();
        long expiryMs = rememberMe ? REMEMBER_ME_EXPIRATION_MS : EXPIRATION_TIME_MS;
        Date expiry = new Date(now.getTime() + expiryMs);

        return Jwts.builder()
                .setSubject(mobileNumber)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractMobileNumber(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}