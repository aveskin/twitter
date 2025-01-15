package ru.aveskin.twitter.user.tweet.web.dto;

import java.time.Instant;

public record TweetResponse(
        long id,
        String message,
        Instant createdTimestamp
) {
}
