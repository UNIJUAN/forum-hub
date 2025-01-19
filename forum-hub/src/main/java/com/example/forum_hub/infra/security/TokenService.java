package com.example.forum_hub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.forum_hub.domain.user.User;
import com.example.forum_hub.infra.security.exceptions.TokenGenerationException;
import com.example.forum_hub.infra.security.exceptions.TokenValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secretKey;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("ForumHub")
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException("Error al generar el token JWT", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new TokenValidationException("Token no puede ser nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("ForumHub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new TokenValidationException("Token JWT inválido o expirado");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(24)
                .toInstant(ZoneOffset.of("-05:00"));
    }
}