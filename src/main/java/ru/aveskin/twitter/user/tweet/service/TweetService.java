package ru.aveskin.twitter.user.tweet.service;

import ru.aveskin.twitter.user.tweet.model.Tweet;

import java.util.Optional;

public interface TweetService {
    Tweet createTweet(Tweet tweet);

    Tweet updateTweet(Tweet tweet);

    Optional<Tweet> findTweetById(long tweetId);

    void deleteTweetById(long id);
}
