package ru.aveskin.twitter.user.profile.usecase;

import ru.aveskin.twitter.user.profile.web.dto.UserProfileRegisterRequest;

public interface UserProfileRegisterUseCase {
    void registerUserProfile(UserProfileRegisterRequest request);
}
