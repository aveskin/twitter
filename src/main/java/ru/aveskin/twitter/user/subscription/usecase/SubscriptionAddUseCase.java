package ru.aveskin.twitter.user.subscription.usecase;

import ru.aveskin.twitter.user.subscription.web.dto.SubscribeRequest;

public interface SubscriptionAddUseCase {
    void subscribe(SubscribeRequest subscribeRequest);
}
