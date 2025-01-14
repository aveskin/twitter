package ru.aveskin.twitter.security.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.security.api.model.CurrentUserApiModel;
import ru.aveskin.twitter.security.api.service.IdentityApiService;
import ru.aveskin.twitter.security.service.UserAccountService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdentityApiServiceImpl implements IdentityApiService {
    private final UserAccountService service;

    @Override
    public Optional<CurrentUserApiModel> currentUserAccount() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }
        String username = authentication.getName();

        return service.findUserByUsername(username)
                .map(userAccount -> new CurrentUserApiModel(userAccount.getId()));
    }
}
