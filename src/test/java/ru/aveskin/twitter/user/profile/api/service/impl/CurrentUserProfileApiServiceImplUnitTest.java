package ru.aveskin.twitter.user.profile.api.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.security.api.model.CurrentUserApiModel;
import ru.aveskin.twitter.security.api.service.IdentityApiService;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.profile.service.UserProfileService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CurrentUserProfileApiServiceImplUnitTest {
    @Mock
    private IdentityApiService identityApiService;
    @Mock
    private UserProfileService userProfileService;
    @InjectMocks
    CurrentUserProfileApiServiceImpl userProfileApiService;

    @Test
    void shouldReturnCurrentUserProfile() {
        Optional<CurrentUserApiModel> currentUserApiModelOptional =
                Optional.of(new CurrentUserApiModel(1L));

        Mockito.when(identityApiService.currentUserAccount())
                .thenReturn(currentUserApiModelOptional);

        UserProfile expectedUserProfile = new UserProfile();
        expectedUserProfile.setId(currentUserApiModelOptional.get().userAccountId());

        Mockito.when(userProfileService.findUserProfileById(currentUserApiModelOptional.get().userAccountId()))
                .thenReturn(Optional.of(expectedUserProfile));


        UserProfile currentUserProfile = userProfileApiService.currentUserProfile();
        Assertions.assertEquals(currentUserProfile, expectedUserProfile);
        Mockito.verify(identityApiService, Mockito.times(1)).currentUserAccount();
        Mockito.verify(userProfileService, Mockito.times(1))
                .findUserProfileById(currentUserApiModelOptional.get().userAccountId());
    }

    @Test
    void shouldThrowTwitterExceptionIfUserDoesNotAuthorized() {
        Mockito.when(identityApiService.currentUserAccount())
                .thenReturn(Optional.empty());

        TwitterException exception = assertThrows(
                TwitterException.class,
                () -> userProfileApiService.currentUserProfile()
        );

        Assertions.assertEquals("Пользователь должен быть атворизован", exception.getMessage());
        Mockito.verify(identityApiService, Mockito.times(1)).currentUserAccount();
        Mockito.verifyNoInteractions(userProfileService);
    }

    @Test
    void shouldThrowTwitterExceptionIfUserProfileDoesNotExist() {
        Optional<CurrentUserApiModel> currentUserApiModelOptional =
                Optional.of(new CurrentUserApiModel(1L));

        Mockito.when(identityApiService.currentUserAccount())
                .thenReturn(currentUserApiModelOptional);

        Mockito.when(userProfileService.findUserProfileById(currentUserApiModelOptional.get().userAccountId()))
                .thenReturn(Optional.empty());

        TwitterException exception = assertThrows(
                TwitterException.class,
                () -> userProfileApiService.currentUserProfile()
        );

        Assertions.assertEquals("Профиля пользователя с id = 1 не сущестует", exception.getMessage());
        Mockito.verify(identityApiService, Mockito.times(1)).currentUserAccount();
        Mockito.verify(userProfileService, Mockito.times(1))
                .findUserProfileById(currentUserApiModelOptional.get().userAccountId());
    }


}