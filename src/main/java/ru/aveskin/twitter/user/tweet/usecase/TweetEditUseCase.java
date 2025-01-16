package ru.aveskin.twitter.user.tweet.usecase;

import ru.aveskin.twitter.user.tweet.web.dto.TweetEditRequest;
import ru.aveskin.twitter.user.tweet.web.dto.TweetResponse;

public interface TweetEditUseCase {
    TweetResponse editTweet(TweetEditRequest request);
}
