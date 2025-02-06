package ru.aveskin.twitter.security.service;

import org.springframework.security.core.Authentication;

public interface AccessTokenService {
    String generateIdToken(Authentication authentication);
}
