package ru.aveskin.twitter.user.profile.mapper;

import ru.aveskin.twitter.security.mapper.Mapper;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.profile.web.dto.UserProfileRegisterRequest;

public interface UserProfileRegisterRequestToUserProfileMapper extends Mapper<UserProfile, UserProfileRegisterRequest> {
}
