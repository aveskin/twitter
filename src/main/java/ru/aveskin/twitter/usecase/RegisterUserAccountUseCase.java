package ru.aveskin.twitter.usecase;

import ru.aveskin.twitter.sequrity.web.dto.RegisterRequest;

public interface RegisterUserAccountUseCase {
    void register(RegisterRequest request);
}
