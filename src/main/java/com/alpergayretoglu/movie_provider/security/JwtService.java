package com.alpergayretoglu.movie_provider.security;

import com.alpergayretoglu.movie_provider.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY;
    private final String TOKEN_ISSUER;
    private final int EXPIRE_HOURS; // token expires in given hours

    public JwtService(SecurityConstants securityConstants) {
        SECRET_KEY = securityConstants.getJWT_SECRET_KEY();
        TOKEN_ISSUER = securityConstants.getJWT_TOKEN_ISSUER();
        EXPIRE_HOURS = securityConstants.getJWT_EXPIRATION_HOURS();
    }

    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // TODO: What if no algorithm? Vulnerability by giving json with no alg
    public String generateToken(User user) {
        return Jwts.builder()
                .signWith(getSigningKey())
                .setIssuer(TOKEN_ISSUER)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * EXPIRE_HOURS))
                .compact();
    }

    /**
     * Takes token from Bearer header and returns Jws parsed claims.
     *
     * @param authHeader whole header like "Bearer eyJhbGciOiJ.."
     * @return Jws Parsed Claims
     */
    public Jws<Claims> verifyAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;

        String token = authHeader.substring(7);

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}