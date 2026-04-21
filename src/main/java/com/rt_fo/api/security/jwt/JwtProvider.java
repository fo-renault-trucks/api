package com.rt_fo.api.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtProvider {

    private final int duration;
    private final JwtEncoder jwtEncoder;

    public JwtProvider(@Value("${jwt.duration:86400}") int duration, JwtEncoder jwtEncoder) {
        this.duration = duration;
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(duration))
                .subject(authentication.getName())
                .claim(JwtClaims.ROLES,
                        authentication.getAuthorities()
                                .stream()
                                .filter(authority -> authority instanceof SimpleGrantedAuthority) // By default, Spring Boot automatically adds a FACTOR_PASSWORD authority
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(" "))
                )
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}
