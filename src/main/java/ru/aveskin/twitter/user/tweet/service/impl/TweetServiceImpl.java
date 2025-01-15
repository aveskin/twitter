package ru.aveskin.twitter.user.tweet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.repository.TweetRepository;
import ru.aveskin.twitter.user.tweet.service.TweetService;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    private final TweetRepository repository;

    @Override
    public Tweet createTweet(Tweet tweet) {
        return repository.save(tweet);
    }
}
