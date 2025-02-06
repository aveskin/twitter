package ru.aveskin.twitter.user.tweet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.tweet.service.TweetService;
import ru.aveskin.twitter.user.tweet.usecase.TweetDeleteUseCase;
import ru.aveskin.twitter.user.tweet.web.dto.TweetDeleteRequest;

@Component
@RequiredArgsConstructor
public class TweetDeleteUseCaseFacade implements TweetDeleteUseCase {
    private final TweetService tweetService;
    private final CurrentUserProfileApiService currentUserProfileApiService;

    @Override
    public void deleteTweet(TweetDeleteRequest request) {
        UserProfile actor = currentUserProfileApiService.currentUserProfile();

        UserProfile owner = tweetService
                .findTweetById(request.id())
                .orElseThrow(() -> {
                    String errorMessage = String
                            .format("твит с id = %d не найден, удаление невозможно",
                                    request.id());
                    return new TwitterException(errorMessage);
                })
                .getUserProfile();

        if (!actor.equals(owner)) {
            throw new TwitterException("Пользователь не является владельцем твита, удаление невозможно");
        }

        tweetService.deleteTweetById(request.id());
    }
}
