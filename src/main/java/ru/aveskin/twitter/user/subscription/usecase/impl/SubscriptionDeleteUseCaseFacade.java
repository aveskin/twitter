package ru.aveskin.twitter.user.subscription.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.subscription.mapper.UnsubscribeRequestToSubscriptionMapper;
import ru.aveskin.twitter.user.subscription.model.Subscription;
import ru.aveskin.twitter.user.subscription.service.SubscriptionService;
import ru.aveskin.twitter.user.subscription.usecase.SubscriptionDeleteUseCase;
import ru.aveskin.twitter.user.subscription.web.dto.UnsubscribeRequest;

@Component
@RequiredArgsConstructor
public class SubscriptionDeleteUseCaseFacade implements SubscriptionDeleteUseCase {
    private final SubscriptionService subscriptionService;
    private final UnsubscribeRequestToSubscriptionMapper unsubscribeRequestToSubscriptionMapper;

    @Override
    public void unsubscribe(UnsubscribeRequest unsubscribeRequest) {
        Subscription subscription = unsubscribeRequestToSubscriptionMapper.map(unsubscribeRequest);

        UserProfile follower = subscription.getFollower();
        UserProfile followed = subscription.getFollowed();

        if (follower.equals(followed)) {
            throw new RuntimeException("Пользователь не может отписаться сам от себя");
        }

        if (!subscriptionService.existSubscription(subscription)) {
            throw new RuntimeException("Пользователь уже не подписан");
        }

        subscriptionService.deleteSubscription(subscription);
    }
}
