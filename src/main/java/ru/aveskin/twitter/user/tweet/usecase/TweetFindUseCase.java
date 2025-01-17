package ru.aveskin.twitter.user.tweet.usecase;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.aveskin.twitter.user.tweet.web.dto.TweetFindRequest;
import ru.aveskin.twitter.user.tweet.web.dto.TweetPageResponse;


@Validated
public interface TweetFindUseCase {
    TweetPageResponse findTweets(@Valid TweetFindRequest tweetFindRequest);
}
