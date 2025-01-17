package ru.aveskin.twitter.user.tweet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.tweet.model.Tweet;

import java.util.Optional;

public interface TweetService {
    Tweet createTweet(Tweet tweet);

    Tweet updateTweet(Tweet tweet);

    Optional<Tweet> findTweetById(long tweetId);

    void deleteTweetById(long id);

    Page<Tweet> findAllTweets(UserProfile owner, Pageable pageable);
}
