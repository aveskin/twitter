package ru.aveskin.twitter.user.tweet.mapper.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.tweet.mapper.TweetPageToTweetPageResponseMapper;
import ru.aveskin.twitter.user.tweet.mapper.TweetToTweetResponseMapper;
import ru.aveskin.twitter.user.tweet.model.Tweet;
import ru.aveskin.twitter.user.tweet.web.dto.TweetPageResponse;
import ru.aveskin.twitter.user.tweet.web.dto.TweetResponse;

import java.util.Collection;

@Component
@AllArgsConstructor
public class TweetPageToTweetPageResponseMapperImpl implements TweetPageToTweetPageResponseMapper {
    private final TweetToTweetResponseMapper tweetToTweetResponseMapper;

    @Override
    public TweetPageResponse map(Page<Tweet> source) {
        Collection<TweetResponse> tweetPageResponses = source
                .stream()
                .map(tweetToTweetResponseMapper::map)
                .toList();

        return new TweetPageResponse(
                source.getTotalElements(),
                source.isFirst(),
                source.isLast(),
                tweetPageResponses);
    }
}
