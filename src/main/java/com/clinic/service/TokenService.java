package com.clinic.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {
  @Value("${security.jwt.secret:VGhpcyBpcyBhIHNhbXBsZSBzZWNyZXQgc3RyaW5nIGJhc2U2NA==}")
  private String secret;

  public String generateToken(String email){
    return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public SecretKey getSigningKey(){
    byte[] key = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(key);
  }

  public void validateBearer(String auth){
    if (auth == null || !auth.startsWith("Bearer "))
      throw new IllegalArgumentException("Missing or invalid Authorization header");
  }
}
