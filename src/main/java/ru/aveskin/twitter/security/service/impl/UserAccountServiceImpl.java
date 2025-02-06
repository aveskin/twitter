package ru.aveskin.twitter.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.security.model.UserAccount;
import ru.aveskin.twitter.security.repository.UserAccountRepository;
import ru.aveskin.twitter.security.service.UserAccountService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public void createUserAccount(UserAccount userAccount) {
        if (userAccountRepository.existsByUsername(userAccount.getUsername())) {
            throw new TwitterException("Account with this username has already existed");
        }
        userAccountRepository.save(userAccount);
    }

    @Override
    public Optional<UserAccount> findUserByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }
}
