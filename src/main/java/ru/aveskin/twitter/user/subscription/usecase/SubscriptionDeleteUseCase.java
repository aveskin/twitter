package ru.aveskin.twitter.user.subscription.usecase;

import ru.aveskin.twitter.user.subscription.web.dto.UnsubscribeRequest;

public interface SubscriptionDeleteUseCase {
    void unsubscribe(UnsubscribeRequest unsubscribeRequest);
}
