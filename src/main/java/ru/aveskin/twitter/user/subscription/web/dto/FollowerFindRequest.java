package ru.aveskin.twitter.user.subscription.web.dto;

import jakarta.validation.constraints.Min;

public record FollowerFindRequest(
        @Min(0)
        int page,
        @Min(20)
        int limit
) {
}
