package ru.aveskin.twitter.user.tweet.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.tweet.mapper.TweetEditRequestToTweetMapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.service.TweetService;
import ru.aveskin.twitter.user.tweet.web.dto.TweetEditRequest;

@Component
@RequiredArgsConstructor
public class TweetEditRequestToTweetMapperImpl implements TweetEditRequestToTweetMapper {
    private final TweetService service;

    @Override
    public Tweet map(TweetEditRequest request) {
        Tweet currentTweet = service
                .findTweetById(request.id())
                .orElseThrow(() -> new RuntimeException("Твита по данному id не существет в БД"));

        currentTweet.setMessage(request.message());

        return currentTweet;
    }
}
