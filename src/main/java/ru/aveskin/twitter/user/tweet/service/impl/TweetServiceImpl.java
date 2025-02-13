package ru.aveskin.twitter.user.tweet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.repository.TweetRepository;
import ru.aveskin.twitter.user.tweet.service.TweetService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    private final TweetRepository repository;

    @Override
    public Tweet createTweet(Tweet tweet) {
        return repository.save(tweet);
    }

    @Override
    public Tweet updateTweet(Tweet tweet) {
        return repository.save(tweet);
    }

    @Override
    public Optional<Tweet> findTweetById(long tweetId) {
        return repository.findById(tweetId);
    }

    @Override
    public void deleteTweetById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Tweet> findAllTweets(UserProfile owner, Pageable pageable) {
        return repository.findAllByUserProfile(owner, pageable);
    }
}
