package ru.aveskin.twitter.user.profile.api.service;

import ru.aveskin.twitter.user.profile.model.UserProfile;

public interface UserProfileApiService {
    UserProfile findUserProfileById(long userProfileId);
}
