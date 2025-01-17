package ru.aveskin.twitter.user.tweet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.tweet.mapper.TweetPageToTweetPageResponseMapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.service.TweetService;
import ru.aveskin.twitter.user.tweet.usecase.TweetFindUseCase;
import ru.aveskin.twitter.user.tweet.web.dto.TweetFindRequest;
import ru.aveskin.twitter.user.tweet.web.dto.TweetPageResponse;

import static ru.aveskin.twitter.user.tweet.model.Tweet_.CREATED_TIMESTAMP;

@Component
@RequiredArgsConstructor
public class TweetFindUseCaseFacade implements TweetFindUseCase {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final TweetService tweetService;
    private final TweetPageToTweetPageResponseMapper tweetPageToTweetPageResponseMapper;

    @Override
    public TweetPageResponse findTweets(TweetFindRequest findRequest) {
        UserProfile owner = currentUserProfileApiService.currentUserProfile();

        Sort sort = Sort.by(Sort.Direction.DESC, CREATED_TIMESTAMP);

        Pageable pageable = PageRequest.of(findRequest.page(), findRequest.limit(), sort);

        Page<Tweet> pageableTweetResult = tweetService.findAllTweets(owner, pageable);

        return tweetPageToTweetPageResponseMapper.map(pageableTweetResult);
    }

}
