package ru.aveskin.twitter.user.tweet.mapper;

import ru.aveskin.twitter.common.mapper.Mapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.web.dto.TweetEditRequest;

public interface TweetEditRequestToTweetMapper extends Mapper<Tweet, TweetEditRequest> {

}
