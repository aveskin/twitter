package ru.aveskin.twitter.user.subscription.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.subscription.model.FollowerSubscription;
import ru.aveskin.twitter.user.subscription.model.Subscription;
import ru.aveskin.twitter.user.subscription.repository.SubscriptionRepository;
import ru.aveskin.twitter.user.subscription.service.SubscriptionService;


@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void createSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Subscription subscription) {
        UserProfile followed = subscription.getFollowed();
        UserProfile follower = subscription.getFollower();

        subscriptionRepository
                .findByFollowerAndFollowed(follower, followed)
                .ifPresent(subscriptionRepository::delete);
    }

    @Override
    public boolean existSubscription(Subscription subscription) {
        return subscriptionRepository.existsByFollowerAndFollowed(subscription.getFollower(),
                subscription.getFollowed());
    }

    @Override
    public Page<FollowerSubscription> findAllFollowerSubscriptions(UserProfile author, Pageable pageable) {
        return subscriptionRepository.findAllByFollowed(author, pageable);
    }
}
