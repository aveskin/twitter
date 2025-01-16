package ru.aveskin.twitter.user.tweet.web.dto;

import jakarta.validation.constraints.NotNull;

public record TweetDeleteRequest(
        @NotNull
        long id
) {
}
