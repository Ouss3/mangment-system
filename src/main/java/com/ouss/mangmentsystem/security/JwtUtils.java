package com.ouss.mangmentsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

/**
 * Project Name: MangmentSystem
 * File Name: JwtUtils
 * Created by: DELL
 * Created on: 12/15/2024
 * Description:
 * <p>
 * JwtUtils is a part of the MangmentSystem project.
 */
@Service
@Slf4j
public class JwtUtils {

    private static final long EXPIRATION_TIME_IN_MILLISECONDS = 864000000L;
    private SecretKey secretKey;

    @Value("${jwt.secret}")
    public String secertJwtString;

    @PostConstruct
    private void init() {
        byte[] secretBytes = secertJwtString.getBytes(StandardCharsets.UTF_8);
        this.secretKey = new SecretKeySpec(secretBytes, "HmacSHA256");
          }


    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLISECONDS))
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
       return claimsResolver.apply(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload());


    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

}
