package ru.aveskin.twitter.user.tweet.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TweetAddRequest(
        @NotBlank
        @Size(min = 10, max = 180)
        String message) {
}
