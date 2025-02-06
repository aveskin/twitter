package ru.aveskin.twitter.security.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.security.mapper.RegisterRequestToUserAccountMapper;
import ru.aveskin.twitter.security.model.UserAccount;
import ru.aveskin.twitter.security.model.UserRole;
import ru.aveskin.twitter.security.service.UserRoleService;
import ru.aveskin.twitter.security.web.dto.RegisterRequest;

import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class RegisterRequestToUserAccountMapperImpl implements RegisterRequestToUserAccountMapper {

    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccount map(RegisterRequest request) {
        UserRole userRole = userRoleService
                .findUserRole()
                .orElseThrow(() -> new TwitterException("User role not found"));

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(request.username().toLowerCase(Locale.ROOT));
        userAccount.setPassword(passwordEncoder.encode(request.password()));
        userAccount.setAuthorities(List.of(userRole));
        return userAccount;
    }
}
