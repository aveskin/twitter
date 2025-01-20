package ru.aveskin.twitter.user.profile.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.user.profile.api.service.UserProfileApiService;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.profile.service.UserProfileService;

@Service
@RequiredArgsConstructor
public class UserProfileApiServiceImpl implements UserProfileApiService {
    private final UserProfileService userProfileService;

    @Override
    public UserProfile findUserProfileById(long userProfileId) {
        return userProfileService
                .findUserProfileById(userProfileId)
                .orElseThrow(() -> {
                    String errorMessage = String
                            .format("Профиля пользователя с id = %d не сущестует", userProfileId
                            );
                    return new RuntimeException(errorMessage);
                });
    }
}
