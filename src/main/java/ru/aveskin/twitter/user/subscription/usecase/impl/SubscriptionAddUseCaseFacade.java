package ru.aveskin.twitter.user.subscription.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.subscription.mapper.SubscribeRequestToSubscriptionMapper;
import ru.aveskin.twitter.user.subscription.model.Subscription;
import ru.aveskin.twitter.user.subscription.service.SubscriptionService;
import ru.aveskin.twitter.user.subscription.usecase.SubscriptionAddUseCase;
import ru.aveskin.twitter.user.subscription.web.dto.SubscribeRequest;

@Component
@RequiredArgsConstructor
public class SubscriptionAddUseCaseFacade implements SubscriptionAddUseCase {
    private final SubscriptionService subscriptionService;
    private final SubscribeRequestToSubscriptionMapper subscribeRequestToSubscriptionMapper;

    @Override
    public void subscribe(SubscribeRequest subscribeRequest) {
        Subscription subscription = subscribeRequestToSubscriptionMapper.map(subscribeRequest);
        UserProfile follower = subscription.getFollower();
        UserProfile followed = subscription.getFollowed();

        if (follower.equals(followed)) {
            throw new RuntimeException("Пользователь не может подписаться сам на себя");
        }

        if (subscriptionService.existSubscription(subscription)) {
            throw new RuntimeException("Пользователь уже имеет данную подписку");
        }

        subscriptionService.createSubscription(subscription);
    }
}
