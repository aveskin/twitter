package ru.aveskin.twitter.user.subscription.model;

import ru.aveskin.twitter.user.profile.model.UserProfile;

import java.time.Instant;

public interface FollowerSubscription {
    long getId();

    UserProfile getFollower();

    Instant getCreatedTimestamp();
}
