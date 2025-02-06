package ru.aveskin.twitter.security.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.security.service.AccessTokenService;
import ru.aveskin.twitter.security.usecase.AuthenticationUseCase;
import ru.aveskin.twitter.security.web.dto.AccessToken;
import ru.aveskin.twitter.security.web.dto.LoginRequest;

@Component
@RequiredArgsConstructor
public class AuthenticationUseCaseFacade implements AuthenticationUseCase {
    private final AuthenticationManager authenticationManager;
    private final AccessTokenService tokenService;

    @Override
    public AccessToken authenticate(LoginRequest request) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return new AccessToken(tokenService.generateIdToken(authentication));
    }
}
