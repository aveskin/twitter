package ru.aveskin.twitter.user.profile.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.security.api.model.CurrentUserApiModel;
import ru.aveskin.twitter.security.api.service.IdentityApiService;
import ru.aveskin.twitter.user.profile.mapper.UserProfileRegisterRequestToUserProfileMapper;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.profile.web.dto.UserProfileRegisterRequest;

@Component
@RequiredArgsConstructor
public class UserProfileRegisterRequestToUserProfileMapperImpl
        implements UserProfileRegisterRequestToUserProfileMapper {

    private final IdentityApiService identityApiService;

    @Override
    public UserProfile map(UserProfileRegisterRequest request) {
        CurrentUserApiModel currentUserApiModel = identityApiService
                .currentUserAccount()
                .orElseThrow(() -> new TwitterException("Для создания профиля, пользователь должен быть авторизован"));

        UserProfile userProfile = new UserProfile();
        userProfile.setId(currentUserApiModel.userAccountId());
        userProfile.setNickname(request.nickname());
        userProfile.setImageLink(request.imageLink());

        return userProfile;
    }
}
