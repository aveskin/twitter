package ru.aveskin.twitter.security.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AccessTokenServiceImplUntTest {
    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private AccessTokenServiceImpl accessTokenService;

    @BeforeEach
    void setUp() {
        // Настройка моков для authentication и userDetails
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("testUser");
    }

    @Test
    void testGenerateIdToken() {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(1, ChronoUnit.DAYS);

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .claim("scope", List.of("ROLE_USER"))
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .subject("testUser")
                .build();

        Jwt jwt = Jwt.withTokenValue("mockTokenValue")
                .headers(headers -> headers.put("alg", "none"))
                .claims(claims -> claims.putAll(claimsSet.getClaims()))
                .build();

        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwt);

        String token = accessTokenService.generateIdToken(authentication);

        assertEquals("mockTokenValue", token);
        verify(jwtEncoder, times(1)).encode(any(JwtEncoderParameters.class));
        verify(authentication, times(1)).getPrincipal();
        verify(userDetails, times(1)).getUsername();
        verify(userDetails, times(1)).getAuthorities();
    }
}