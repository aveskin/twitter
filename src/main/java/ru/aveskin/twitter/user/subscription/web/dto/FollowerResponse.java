package ru.aveskin.twitter.user.subscription.web.dto;

import java.time.Instant;

public record FollowerResponse(
        long subscriptionId,
        long followerId,
        String followerNickname,
        String followerImageLink,
        Instant createdTimestamp
) {
}
