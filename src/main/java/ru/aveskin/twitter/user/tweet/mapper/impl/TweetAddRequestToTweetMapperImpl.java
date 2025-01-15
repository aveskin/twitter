package ru.aveskin.twitter.user.tweet.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.aveskin.twitter.user.tweet.mapper.TweetAddRequestToTweetMapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.web.dto.TweetAddRequest;

@Component
@RequiredArgsConstructor
public class TweetAddRequestToTweetMapperImpl implements TweetAddRequestToTweetMapper {
    private final CurrentUserProfileApiService service;

    @Override
    public Tweet map(TweetAddRequest request) {
        Tweet tweet = new Tweet();
        tweet.setUserProfile(service.currentUserProfile());
        tweet.setMessage(request.message());

        return tweet;
    }
}
