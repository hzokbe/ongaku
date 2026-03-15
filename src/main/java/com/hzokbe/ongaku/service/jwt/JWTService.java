package com.hzokbe.ongaku.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.javalin.http.InternalServerErrorResponse;
import io.javalin.http.UnauthorizedResponse;

import java.time.Instant;

public class JWTService {
    private final String jwtSecret;

    public JWTService() {
        var jwtSecret = System.getenv("JWT_SECRET");

        if (jwtSecret == null) {
            throw new InternalServerErrorResponse("JWT secret is not defined");
        }

        this.jwtSecret = jwtSecret;
    }

    public void verify(String jwt) {
        try {
            var verifier = JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer("ongaku")
                    .build();

            verifier.verify(jwt);
        } catch (JWTVerificationException exception) {
            throw new UnauthorizedResponse("JWT is invalid or expired");
        }
    }

    public String generate(String username) {
        var now = Instant.now();

        return JWT
                .create()
                .withIssuer("ongaku")
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(now.plusSeconds(3_600))
                .sign(Algorithm.HMAC256(jwtSecret));
    }
}
