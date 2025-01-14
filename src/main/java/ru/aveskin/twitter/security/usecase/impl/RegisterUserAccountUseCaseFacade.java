package ru.aveskin.twitter.security.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.security.mapper.RegisterRequestToUserAccountMapper;
import ru.aveskin.twitter.security.model.UserAccount;
import ru.aveskin.twitter.security.service.UserAccountService;
import ru.aveskin.twitter.security.usecase.RegisterUserAccountUseCase;
import ru.aveskin.twitter.security.web.dto.RegisterRequest;

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
