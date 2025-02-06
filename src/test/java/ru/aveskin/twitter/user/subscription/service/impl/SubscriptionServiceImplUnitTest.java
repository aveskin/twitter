package ru.aveskin.twitter.user.subscription.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.subscription.model.Subscription;
import ru.aveskin.twitter.user.subscription.repository.SubscriptionRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplUnitTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    SubscriptionServiceImpl subscriptionService;

    @Test
    void shouldCreateSubscription() {
        Subscription subscription = new Subscription();
        subscription.setId(1L);

        subscriptionService.createSubscription(subscription);
        Mockito.verify(subscriptionRepository, Mockito.times(1)).save(subscription);
    }

    @Test
    void shouldDeleteSubscription() {
        Subscription subscription = new Subscription();
        subscription.setId(1L);

        UserProfile followed = new UserProfile();
        followed.setId(1L);
        UserProfile follower = new UserProfile();
        follower.setId(2L);

        subscription.setFollowed(followed);
        subscription.setFollower(follower);

        Mockito.when(subscriptionRepository.findByFollowerAndFollowed(follower, followed))
                .thenReturn(Optional.of(subscription));

        subscriptionService.deleteSubscription(subscription);
        Mockito.verify(subscriptionRepository, Mockito.times(1)).delete(subscription);
    }

    @Test
    void shouldReturnTrueIfSubscriptionExists() {
        Subscription subscription = new Subscription();
        subscription.setId(1L);
        UserProfile followed = new UserProfile();
        followed.setId(1L);
        UserProfile follower = new UserProfile();
        follower.setId(2L);
        subscription.setFollowed(followed);
        subscription.setFollower(follower);

        Mockito.when(subscriptionRepository.existsByFollowerAndFollowed(follower, followed))
                .thenReturn(true);

        boolean expectedResult = subscriptionService.existSubscription(subscription);

        Assertions.assertTrue(expectedResult);
        Mockito.verify(subscriptionRepository, Mockito.times(1))
                .existsByFollowerAndFollowed(follower, followed);
    }

    @Test
    void shouldReturnFalseWhenSubscriptionIsNotFound() {
        Subscription subscription = new Subscription();
        subscription.setId(1L);
        UserProfile followed = new UserProfile();
        followed.setId(1L);
        UserProfile follower = new UserProfile();
        follower.setId(2L);
        subscription.setFollowed(followed);
        subscription.setFollower(follower);

        Mockito.when(subscriptionRepository.existsByFollowerAndFollowed(follower, followed))
                .thenReturn(false);

        boolean expectedResult = subscriptionService.existSubscription(subscription);

        Assertions.assertFalse(expectedResult);
        Mockito.verify(subscriptionRepository, Mockito.times(1))
                .existsByFollowerAndFollowed(follower, followed);
    }


}