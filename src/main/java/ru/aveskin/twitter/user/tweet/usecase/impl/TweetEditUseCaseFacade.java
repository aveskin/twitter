package ru.aveskin.twitter.user.tweet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.tweet.mapper.TweetEditRequestToTweetMapper;
import ru.aveskin.twitter.user.tweet.mapper.TweetToTweetResponseMapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.service.TweetService;
import ru.aveskin.twitter.user.tweet.usecase.TweetEditUseCase;
import ru.aveskin.twitter.user.tweet.web.dto.TweetEditRequest;
import ru.aveskin.twitter.user.tweet.web.dto.TweetResponse;

@Component
@RequiredArgsConstructor
public class TweetEditUseCaseFacade implements TweetEditUseCase {
    private final TweetService service;
    private final TweetEditRequestToTweetMapper tweetEditRequestToTweetMapper;
    private final TweetToTweetResponseMapper tweetToTweetResponseMapper;
    private final CurrentUserProfileApiService currentUserProfileApiService;

    @Override
    public TweetResponse editTweet(TweetEditRequest request) {
        UserProfile actor = currentUserProfileApiService.currentUserProfile();

        UserProfile owner = service
                .findTweetById(request.id())
                .map(Tweet::getUserProfile)
                .orElseThrow(() -> new TwitterException("Твита по данному id не существет в БД"));

        if (!actor.equals(owner)) {
            throw new TwitterException("Тивит не принадлежит пользователю, редактирование запрещено");
        }

        Tweet udatedTweet = service.updateTweet(tweetEditRequestToTweetMapper.map(request));

        return tweetToTweetResponseMapper.map(udatedTweet);
    }
}
