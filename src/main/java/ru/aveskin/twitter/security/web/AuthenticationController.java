package ru.aveskin.twitter.security.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aveskin.twitter.security.usecase.AuthenticationUseCase;
import ru.aveskin.twitter.security.web.dto.AccessToken;
import ru.aveskin.twitter.security.web.dto.LoginRequest;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/access_token")
    AccessToken getToken(@Valid @RequestBody LoginRequest request) {
        return authenticationUseCase.authenticate(request);
    }

}
