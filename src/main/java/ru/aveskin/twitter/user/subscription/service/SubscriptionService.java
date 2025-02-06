package ru.aveskin.twitter.user.subscription.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.subscription.model.FollowerSubscription;
import ru.aveskin.twitter.user.subscription.model.Subscription;

public interface SubscriptionService {
    void createSubscription(Subscription subscription);

    void deleteSubscription(Subscription subscription);

    boolean existSubscription(Subscription subscription);

    Page<FollowerSubscription> findAllFollowerSubscriptions(UserProfile author, Pageable pageable);
}
