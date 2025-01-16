package ru.aveskin.twitter.user.tweet.usecase;

import ru.aveskin.twitter.user.tweet.web.dto.TweetDeleteRequest;

public interface TweetDeleteUseCase {
    void deleteTweet(TweetDeleteRequest request);
}
