package ru.aveskin.twitter.sequrity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.sequrity.model.UserAccount;
import ru.aveskin.twitter.sequrity.repository.UserAccountRepository;
import ru.aveskin.twitter.sequrity.service.UserAccountService;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public void createUserAccount(UserAccount userAccount) {
        if (userAccountRepository.existsByUsername(userAccount.getUsername())) {
            throw new RuntimeException("Account with this username has already existed");
        }
        userAccountRepository.save(userAccount);
    }
}
