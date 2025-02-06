package ru.aveskin.twitter.security.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.security.model.UserAccount;
import ru.aveskin.twitter.security.model.UserRole;
import ru.aveskin.twitter.security.repository.UserAccountRepository;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceImplUnitTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private UserAccountServiceImpl userAccountService;

    @Test
    void shouldSaveUserAccountIfUsernameDoesNotExist() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("test@gmail.com");
        userAccount.setPassword("test_password");
        userAccount.setAuthorities(Collections.singletonList(new UserRole()));

        Mockito.when(userAccountRepository.existsByUsername(userAccount.getUsername()))
                .thenReturn(false);

        userAccountService.createUserAccount(userAccount);

        Mockito.verify(userAccountRepository, Mockito.times(1)).save(userAccount);
    }

    @Test
    void shouldThrowTwitterExceptionIfUsernameAlreadyExists() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("test@gmail.com");
        userAccount.setPassword("test_password");
        userAccount.setAuthorities(Collections.singletonList(new UserRole()));

        Mockito.when(userAccountRepository.existsByUsername(userAccount.getUsername()))
                .thenReturn(true);

        TwitterException exception = Assertions.assertThrows(
                TwitterException.class,
                () -> userAccountService.createUserAccount(userAccount)
        );

        Assertions.assertEquals("Account with this username has already existed", exception.getMessage());

        Mockito.verify(userAccountRepository, Mockito.never()).save(Mockito.any(UserAccount.class));
    }

    @Test
    void shouldReturnUserAccountByUsername() {
        String username = "test_username";
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        Optional<UserAccount> expectedUserAccount = Optional.of(userAccount);

        Mockito.when(userAccountRepository.findByUsername(username))
                .thenReturn(Optional.of(userAccount));

        Optional<UserAccount> actualUserAccount = userAccountService.findUserByUsername(username);

        Assertions.assertEquals(actualUserAccount, expectedUserAccount);
        Mockito.verify(userAccountRepository, Mockito.times(1)).findByUsername(username);
    }

}