package ru.aveskin.twitter.user.profile.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.profile.repository.UserProfileRepository;
import ru.aveskin.twitter.user.profile.service.UserProfileService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Override
    public void createUserProfile(UserProfile userProfile) {
        if (userProfileRepository.existsById(userProfile.getId())) {
            String errorMessage = String
                    .format("Профиль пользователя с данным id = %d уже существует", userProfile.getId());
            throw new TwitterException(errorMessage);
        }

        if (userProfileRepository.existsByNickname(userProfile.getNickname())) {
            String errorMessage = String
                    .format("Профиль пользователя с данным nickname = %s уже существует", userProfile.getNickname());
            throw new TwitterException(errorMessage);
        }
        userProfileRepository.save(userProfile);
    }

    @Override
    public Optional<UserProfile> findUserProfileById(long userProfileId) {
        return userProfileRepository.findById(userProfileId);
    }
}
