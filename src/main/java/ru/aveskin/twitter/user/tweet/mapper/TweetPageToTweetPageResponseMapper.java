package ru.aveskin.twitter.user.tweet.mapper;

import org.springframework.data.domain.Page;
import ru.aveskin.twitter.common.mapper.Mapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.web.dto.TweetPageResponse;

public interface TweetPageToTweetPageResponseMapper extends Mapper<TweetPageResponse, Page<Tweet>> {
}
