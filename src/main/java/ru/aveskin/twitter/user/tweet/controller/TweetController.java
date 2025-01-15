package ru.aveskin.twitter.user.tweet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aveskin.twitter.user.tweet.usecase.TweetAddUseCase;
import ru.aveskin.twitter.user.tweet.web.dto.TweetAddRequest;
import ru.aveskin.twitter.user.tweet.web.dto.TweetResponse;

@RestController
@RequestMapping("/api/v1/tweets")
@RequiredArgsConstructor
public class TweetController {
    private final TweetAddUseCase tweetAddUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponse addTweet(@Valid @RequestBody TweetAddRequest request) {
        return tweetAddUseCase.addTweet(request);
    }

}
