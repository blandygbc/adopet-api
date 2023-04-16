package com.blandygbc.adopet.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.blandygbc.adopet.domain.exception.ApiJWTException;
import com.blandygbc.adopet.domain.exception.CreateJWTException;
import com.blandygbc.adopet.domain.exception.TokenJWTExpiredException;
import com.blandygbc.adopet.domain.user.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String JWT_ISSUER = "Adopet API";

    public String createToken(User user) {
        try {
            var algorithm = Algorithm.HMAC512(secret);
            return JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new CreateJWTException("Não foi possível criar o token: " + exception.getLocalizedMessage());
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC512(secret);
            return JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            if (exception.getMessage().startsWith("The Token has expired")) {
                throw new TokenJWTExpiredException(exception.getMessage());
            }
            throw new ApiJWTException("Erro ao verificar o token:" + exception.getMessage());
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
