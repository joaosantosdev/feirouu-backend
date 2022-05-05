package com.joaosantosdev.feirouu.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
                .signWith(SignatureAlgorithm.HS256, this.secret.getBytes())
                .compact();
    }

    public boolean validateToken(String token){
        Claims claims = getClaims(token);
        if(claims != null){
            String email = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return email != null && expiration != null && now.before(expiration);
        }
        return false;
    }

    private Claims getClaims(String token) {
       try{
           return Jwts.parser()
                   .setSigningKey(this.secret.getBytes())
                   .parseClaimsJws(token)
                   .getBody();
       }catch (Exception e){
           return null;
       }
    }

    public String getEmail(String token){
        Claims claims = getClaims(token);
        if(claims != null){
            return claims.getSubject();
        }
        return null;
    }
}
