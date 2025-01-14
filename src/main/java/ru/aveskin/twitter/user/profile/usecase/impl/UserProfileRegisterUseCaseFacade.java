package ru.aveskin.twitter.user.profile.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.profile.mapper.UserProfileRegisterRequestToUserProfileMapper;
import ru.aveskin.twitter.user.profile.service.UserProfileService;
import ru.aveskin.twitter.user.profile.usecase.UserProfileRegisterUseCase;
import ru.aveskin.twitter.user.profile.web.dto.UserProfileRegisterRequest;

@Component
@RequiredArgsConstructor
public class UserProfileRegisterUseCaseFacade implements UserProfileRegisterUseCase {
    private final UserProfileService service;
    private final UserProfileRegisterRequestToUserProfileMapper mapper;

    @Override
    public void registerUserProfile(UserProfileRegisterRequest request) {
        service.createUserProfile(mapper.map(request));
    }
}
