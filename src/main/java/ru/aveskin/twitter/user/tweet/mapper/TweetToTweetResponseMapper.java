package ru.aveskin.twitter.user.tweet.mapper;

import ru.aveskin.twitter.security.mapper.Mapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.web.dto.TweetResponse;

public interface TweetToTweetResponseMapper extends Mapper<TweetResponse, Tweet> {
}
