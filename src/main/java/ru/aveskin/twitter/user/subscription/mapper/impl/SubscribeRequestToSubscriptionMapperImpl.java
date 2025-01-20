package ru.aveskin.twitter.user.subscription.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.aveskin.twitter.user.profile.api.service.UserProfileApiService;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.subscription.mapper.SubscribeRequestToSubscriptionMapper;
import ru.aveskin.twitter.user.subscription.model.Subscription;
import ru.aveskin.twitter.user.subscription.web.dto.SubscribeRequest;

@Component
@RequiredArgsConstructor
public class SubscribeRequestToSubscriptionMapperImpl implements SubscribeRequestToSubscriptionMapper {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final UserProfileApiService userProfileApiService;

    @Override
    public Subscription map(SubscribeRequest subscribeRequest) {
        UserProfile follower = currentUserProfileApiService.currentUserProfile();
        UserProfile followed = userProfileApiService.findUserProfileById(subscribeRequest.followedId());

        Subscription subscription = new Subscription();
        subscription.setFollower(follower);
        subscription.setFollowed(followed);

        return subscription;
    }
}
