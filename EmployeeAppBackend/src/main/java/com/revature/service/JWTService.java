package com.revature.service;


import com.revature.exception.UnauthorizedResponse;
import com.revature.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTService {
    private static JWTService instance;

    private Key key;

    private JWTService() {
        byte[] secret = "my_secret_password_asdfasdfjkljclkvjl13432k2312jlkj3941809df".getBytes();
        key = Keys.hmacShaKeyFor(secret);
    }

    // method
    public static JWTService getInstance() {
        if (JWTService.instance == null) {
            JWTService.instance = new JWTService();
        }

        return JWTService.instance;
    }


    // Signing a JWT with the key
    public String createJWT(User user) {
        String jwt = Jwts.builder()
                .setSubject(user.getUserName())
                .claim("user_id", user.getId())
                .claim("user_role", user.getUserRole())
                .signWith(key)
                .compact();

        return jwt;
    }

    // Validating a JWT with the key
    public Jws<Claims> parseJwt(String jwt) throws UnauthorizedResponse {
        try {
            Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

            return token;
        } catch(JwtException e) {
           // e.printStackTrace();
            throw new UnauthorizedResponse("JWT was invalid");
        }

    }
}
