package ru.aveskin.twitter.security.usecase;

import ru.aveskin.twitter.security.web.dto.AccessToken;
import ru.aveskin.twitter.security.web.dto.LoginRequest;

public interface AuthenticationUseCase {
    AccessToken authenticate(LoginRequest request);
}
