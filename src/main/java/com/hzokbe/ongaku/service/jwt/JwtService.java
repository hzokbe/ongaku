package com.hzokbe.ongaku.service.jwt;

import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.hzokbe.ongaku.model.jwt.response.JwtResponse;

@Service
public class JwtService {
    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public JwtResponse generateJwt(Authentication authentication) {
        var issuedAt = Instant.now();

        var expiresAt = issuedAt.plusSeconds(3600);

        var claims = JwtClaimsSet
            .builder()
            .issuer("ongaku")
            .issuedAt(issuedAt)
            .expiresAt(expiresAt)
            .subject(authentication.getName())
            .claim("roles", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toList())
            .build();

        return new JwtResponse(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    }
}
