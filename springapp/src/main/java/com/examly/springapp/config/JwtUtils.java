package com.examly.springapp.config;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    public static final String jwtSecret = "11f415e363c707e46d02875eff0870cc";
    public static final String JWT_HEADER = "Authorization";
    private final int jwtExpirationMs = 86400000;
    Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+jwtExpirationMs))
        .signWith(key)
        .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); 
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

}
