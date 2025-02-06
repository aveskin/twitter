package ru.aveskin.twitter.user.tweet.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.repository.TweetRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TweetServiceImplUnitTest {

    @Mock
    private TweetRepository tweetRepository;

    @InjectMocks
    TweetServiceImpl tweetService;

    @Test
    void shouldCreateTweet() {
        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setMessage("test message");

        tweetService.createTweet(tweet);
        Mockito.verify(tweetRepository, Mockito.times(1)).save(tweet);
    }

    @Test
    void shouldUpdateTweet() {
        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setMessage("test message");

        tweetService.updateTweet(tweet);
        Mockito.verify(tweetRepository, Mockito.times(1)).save(tweet);
    }

    @Test
    void shouldDeleteTweetById() {
        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setMessage("test message");

        tweetService.deleteTweetById(tweet.getId());
        Mockito.verify(tweetRepository, Mockito.times(1)).deleteById(tweet.getId());
    }

    @Test
    void shouldReturnTweetById() {
        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setMessage("test message");
        Optional<Tweet> expectedResult = Optional.of(tweet);

        Mockito.when(tweetRepository.findById(tweet.getId()))
                .thenReturn(Optional.of(tweet));

        Optional<Tweet> actualTweet = tweetService.findTweetById(tweet.getId());

        Assertions.assertEquals(expectedResult, actualTweet);
        Mockito.verify(tweetRepository, Mockito.times(1)).findById(tweet.getId());

    }

    @Test
    void shouldReturnAllTweetsByUserProfileAndPageable() {
        UserProfile owner = new UserProfile();
        owner.setId(1L);
        owner.setNickname("test_nickname");

        Pageable pageable = PageRequest.of(10, 50);

        List<Tweet> tweets = List.of(new Tweet(), new Tweet());
        Page<Tweet> expectedResult = new PageImpl<>(tweets, pageable, tweets.size());

        Mockito.when(tweetRepository.findAllByUserProfile(owner, pageable))
                .thenReturn(expectedResult);

        Page<Tweet> actualResult = tweetService.findAllTweets(owner, pageable);

        Assertions.assertEquals(expectedResult, actualResult);
        Mockito.verify(tweetRepository, Mockito.times(1)).findAllByUserProfile(owner, pageable);
    }
}