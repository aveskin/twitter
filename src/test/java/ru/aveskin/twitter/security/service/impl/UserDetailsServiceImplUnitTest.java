package ru.aveskin.twitter.security.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.aveskin.twitter.security.mapper.UserAccountToUserMapper;
import ru.aveskin.twitter.security.model.UserAccount;
import ru.aveskin.twitter.security.model.UserRole;
import ru.aveskin.twitter.security.service.UserAccountService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplUnitTest {
    @Mock
    private UserAccountService userAccountService;
    @Mock
    private UserAccountToUserMapper mapper;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void shouldReturnNonEmptyUserDetails() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("test@gmail.com");
        userAccount.setPassword("test_password");
        userAccount.setAuthorities(Collections.singletonList(new UserRole()));

        User expectedResult = new User(
                userAccount.getUsername(),
                userAccount.getPassword(),
                userAccount.getAuthorities()
        );

        Mockito.when(userAccountService.findUserByUsername(userAccount.getUsername()))
                .thenReturn(Optional.of(userAccount));

        Mockito.when(mapper.map(userAccount))
                .thenReturn(expectedResult);

        UserDetails actualResult = userDetailsService
                .loadUserByUsername(userAccount.getUsername());


        assertEquals(expectedResult, actualResult);

        Mockito.verify(userAccountService, Mockito.times(1))
                .findUserByUsername(any());

        Mockito.verify(mapper, Mockito.times(1))
                .map(any());
    }

    @Test
    void shouldThrowUsernameNotFoundException() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("test@gmail.com");
        userAccount.setPassword("test_password");
        userAccount.setAuthorities(Collections.singletonList(new UserRole()));

        Mockito.when(userAccountService.findUserByUsername(userAccount.getUsername()))
                .thenReturn(Optional.empty());

//        assertThrows(
//                UsernameNotFoundException.class,
//                ()-> userDetailsService.loadUserByUsername(userAccount.getUsername())
//        );

        UsernameNotFoundException exception = Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(userAccount.getUsername())
        );

        Assertions.assertEquals("неверные учетные данные пользователя", exception.getMessage());

        Mockito.verify(userAccountService, Mockito.times(1))
                .findUserByUsername(any());

        Mockito.verify(mapper, Mockito.never())
                .map(any());
    }

}