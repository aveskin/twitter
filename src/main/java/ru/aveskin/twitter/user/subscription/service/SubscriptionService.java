package ru.aveskin.twitter.user.subscription.service;

import ru.aveskin.twitter.user.subscription.model.Subscription;

public interface SubscriptionService {
    void createSubscription(Subscription subscription);

    void deleteSubscription(Subscription subscription);

    boolean existSubscription(Subscription subscription);

}
