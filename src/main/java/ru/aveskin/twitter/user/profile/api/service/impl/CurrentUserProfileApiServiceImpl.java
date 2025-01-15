package ru.aveskin.twitter.user.profile.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.security.api.model.CurrentUserApiModel;
import ru.aveskin.twitter.security.api.service.IdentityApiService;
import ru.aveskin.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.profile.service.UserProfileService;

@Service
@RequiredArgsConstructor
public class CurrentUserProfileApiServiceImpl implements CurrentUserProfileApiService {
    private final IdentityApiService identityApiService;
    private final UserProfileService userProfileService;

    @Override
    public UserProfile currentUserProfile() {
        CurrentUserApiModel currentUserApiModel = identityApiService.currentUserAccount()
                .orElseThrow(() -> new RuntimeException("Пользователь должен быть атворизован"));

        return userProfileService
                .findUserProfileById(currentUserApiModel.userAccountId())
                .orElseThrow(() -> {
                    String errorMessage = String
                            .format("Профиля пользователя с id = %d не сущестует",
                                    currentUserApiModel.userAccountId()
                            );
                    return new RuntimeException(errorMessage);
                });
    }
}
