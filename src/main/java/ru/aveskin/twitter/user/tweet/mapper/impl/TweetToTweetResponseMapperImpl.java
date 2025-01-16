package ru.aveskin.twitter.user.tweet.mapper.impl;

import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.tweet.mapper.TweetToTweetResponseMapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.web.dto.TweetResponse;

@Component
public class TweetToTweetResponseMapperImpl implements TweetToTweetResponseMapper {

    @Override
    public TweetResponse map(Tweet tweet) {
        return new TweetResponse(
                tweet.getId(),
                tweet.getMessage(),
                tweet.getCreatedTimestamp(),
                tweet.getModifiedTimestamp()
        );
    }
}
