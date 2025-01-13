package ru.aveskin.twitter.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.sequrity.mapper.RegisterRequestToUserAccountMapper;
import ru.aveskin.twitter.sequrity.model.UserAccount;
import ru.aveskin.twitter.sequrity.service.UserAccountService;
import ru.aveskin.twitter.sequrity.web.dto.RegisterRequest;
import ru.aveskin.twitter.usecase.RegisterUserAccountUseCase;

@Component
@RequiredArgsConstructor
public class RegisterUserAccountUseCaseFacade implements RegisterUserAccountUseCase {
    private final UserAccountService userAccountService;
    private final RegisterRequestToUserAccountMapper mapper;

    @Override
    public void register(RegisterRequest request) {
        UserAccount userAccount = mapper.map(request);
        userAccountService.createUserAccount(userAccount);
    }
}
