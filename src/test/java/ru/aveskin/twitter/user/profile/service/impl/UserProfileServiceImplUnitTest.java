package ru.aveskin.twitter.user.profile.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.profile.repository.UserProfileRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceImplUnitTest {
    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @Test
    void shouldSaveUserProfileIfUserProfileIdAndNicknameDoesNotExist() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setNickname("test_nickname");

        Mockito.when(userProfileRepository.existsById(userProfile.getId()))
                .thenReturn(false);

        Mockito.when(userProfileRepository.existsByNickname(userProfile.getNickname()))
                .thenReturn(false);

        userProfileService.createUserProfile(userProfile);

        Mockito.verify(userProfileRepository, Mockito.times(1)).save(userProfile);
    }

    @Test
    void shouldThrowTwitterExceptionIfUserProfileIdAlreadyExists() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setNickname("test_nickname");

        Mockito.when(userProfileRepository.existsById(userProfile.getId()))
                .thenReturn(true);

        TwitterException exception = Assertions.assertThrows(
                TwitterException.class,
                () -> userProfileService.createUserProfile(userProfile)
        );

        String errorMessage = String
                .format("Профиль пользователя с данным id = %d уже существует", userProfile.getId());

        Assertions.assertEquals(errorMessage, exception.getMessage());

        Mockito.verify(userProfileRepository, Mockito.never()).save(Mockito.any(UserProfile.class));
        Mockito.verify(userProfileRepository, Mockito.never()).existsByNickname(Mockito.any());
    }

    @Test
    void shouldThrowTwitterExceptionIfNicknameAlreadyExists() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setNickname("test_nickname");

        Mockito.when(userProfileRepository.existsById(userProfile.getId()))
                .thenReturn(false);

        Mockito.when(userProfileRepository.existsByNickname(userProfile.getNickname()))
                .thenReturn(true);

        TwitterException exception = Assertions.assertThrows(
                TwitterException.class,
                () -> userProfileService.createUserProfile(userProfile)
        );

        String errorMessage = String
                .format("Профиль пользователя с данным nickname = %s уже существует", userProfile.getNickname());

        Assertions.assertEquals(errorMessage, exception.getMessage());

        Mockito.verify(userProfileRepository, Mockito.never()).save(Mockito.any(UserProfile.class));
        Mockito.verify(userProfileRepository, Mockito.times(1)).existsById(userProfile.getId());

    }

    @Test
    void shouldReturnUserAccountById() {
        long userProfileId = 1L;
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userProfileId);
        Optional<UserProfile> expectedUserProfile = Optional.of(userProfile);

        Mockito.when(userProfileRepository.findById(userProfileId))
                .thenReturn(Optional.of(userProfile));

        Optional<UserProfile> actualUserProfile = userProfileService.findUserProfileById(userProfileId);

        Assertions.assertEquals(actualUserProfile, expectedUserProfile);
        Mockito.verify(userProfileRepository, Mockito.times(1)).findById(userProfileId);
    }


}