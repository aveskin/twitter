package ru.aveskin.twitter.security.usecase;

import ru.aveskin.twitter.security.web.dto.RegisterRequest;

public interface RegisterUserAccountUseCase {
    void register(RegisterRequest request);
}
