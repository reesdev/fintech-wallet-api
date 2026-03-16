package com.portofolio.wallet.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey secretKey =
            Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey12".getBytes());
    private final long jwtExpiration = 100 * 60 * 60; // 1 Hour
    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(secretKey)
                .compact();
    }
    public String extractEmail(String token){
        return extractClaims(token).getSubject();
    }
    private Claims extractClaims (String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public boolean isTokenValid(String token,String email){
        String extractedEmail = extractEmail(token);
        return extractedEmail.equals(email);
    }
}
