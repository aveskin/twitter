package ru.aveskin.twitter.user.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.subscription.model.Subscription;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    boolean existsByFollowerAndFollowed(UserProfile follower, UserProfile followed);

    Optional<Subscription> findByFollowerAndFollowed(UserProfile follower, UserProfile followed);
}
