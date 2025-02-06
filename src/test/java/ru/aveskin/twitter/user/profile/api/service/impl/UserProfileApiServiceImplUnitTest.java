package ru.aveskin.twitter.user.profile.api.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.profile.service.UserProfileService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class UserProfileApiServiceImplUnitTest {
    @Mock
    private UserProfileService userProfileService;

    @InjectMocks
    UserProfileApiServiceImpl userProfileApiService;

    @Test
    void shouldReturnUserProfileByIdIfUserProfileExists() {
        long userProfileId = 1L;
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userProfileId);
        userProfile.setNickname("test_nickname");

        Optional<UserProfile> expectedUserProfile = Optional.of(userProfile);

        Mockito.when(userProfileService.findUserProfileById(userProfileId))
                .thenReturn(expectedUserProfile);

        Optional<UserProfile> actualUserProfile = Optional.of(userProfileApiService.findUserProfileById(userProfileId));

        Assertions.assertEquals(actualUserProfile, expectedUserProfile);
        Mockito.verify(userProfileService, Mockito.times(1)).findUserProfileById(userProfileId);
    }

    @Test
    void shouldThrowTwitterExceptionIfUserProfileDoesNotExist() {
        long userProfileId = 1L;

        Mockito.when(userProfileService.findUserProfileById(userProfileId))
                .thenReturn(Optional.empty());

        TwitterException exception = assertThrows(
                TwitterException.class,
                () -> userProfileApiService.findUserProfileById(userProfileId)
        );

        assertEquals("Профиля пользователя с id = 1 не сущестует", exception.getMessage());
        Mockito.verify(userProfileService, Mockito.times(1)).findUserProfileById(userProfileId);
    }


}