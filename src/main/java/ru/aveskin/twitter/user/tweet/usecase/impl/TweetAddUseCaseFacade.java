package ru.aveskin.twitter.user.tweet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.tweet.mapper.TweetAddRequestToTweetMapper;
import ru.aveskin.twitter.user.tweet.mapper.TweetToTweetResponseMapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.service.TweetService;
import ru.aveskin.twitter.user.tweet.usecase.TweetAddUseCase;
import ru.aveskin.twitter.user.tweet.web.dto.TweetAddRequest;
import ru.aveskin.twitter.user.tweet.web.dto.TweetResponse;

@Component
@RequiredArgsConstructor
public class TweetAddUseCaseFacade implements TweetAddUseCase {
    private final TweetService service;
    private final TweetAddRequestToTweetMapper tweetAddRequestToTweetMapper;
    private final TweetToTweetResponseMapper tweetToTweetResponseMapper;

    @Override
    public TweetResponse addTweet(TweetAddRequest request) {
        Tweet createdTweet = service.createTweet(tweetAddRequestToTweetMapper.map(request));

        return tweetToTweetResponseMapper.map(createdTweet);
    }
}
