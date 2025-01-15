package ru.aveskin.twitter.user.tweet.usecase;

import ru.aveskin.twitter.user.tweet.web.dto.TweetAddRequest;
import ru.aveskin.twitter.user.tweet.web.dto.TweetResponse;

public interface TweetAddUseCase {
    TweetResponse addTweet(TweetAddRequest request);
}
