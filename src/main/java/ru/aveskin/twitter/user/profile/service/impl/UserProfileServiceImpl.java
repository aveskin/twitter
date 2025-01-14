package ru.aveskin.twitter.user.profile.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.profile.repository.UserProfileRepository;
import ru.aveskin.twitter.user.profile.service.UserProfileService;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Override
    public void createUserProfile(UserProfile userProfile) {
        if (userProfileRepository.existsById(userProfile.getId())) {
            String errorMessage = String
                    .format("Профиль пользователя с данным id = %d уже существует", userProfile.getId());
            throw new RuntimeException(errorMessage);
        }

        if (userProfileRepository.existsByNickname(userProfile.getNickname())) {
            String errorMessage = String
                    .format("Профиль пользователя с данным nickname = %s уже существует", userProfile.getNickname());
            throw new RuntimeException(errorMessage);
        }
        userProfileRepository.save(userProfile);
    }
}
