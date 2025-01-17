package ru.aveskin.twitter.user.tweet.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record TweetFindRequest(
        @Min(0) int page,
        @Min(25) @Max(100) int limit
) {
}
