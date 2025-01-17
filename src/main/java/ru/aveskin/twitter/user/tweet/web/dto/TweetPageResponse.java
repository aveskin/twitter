package ru.aveskin.twitter.user.tweet.web.dto;

import java.util.Collection;

public record TweetPageResponse(
        long totalTweets,
        boolean isFirstPage,
        boolean isLastPage,
        Collection<TweetResponse> tweets
) {
}
