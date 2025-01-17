package ru.aveskin.twitter.user.tweet.web;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aveskin.twitter.user.tweet.usecase.TweetAddUseCase;
import ru.aveskin.twitter.user.tweet.usecase.TweetDeleteUseCase;
import ru.aveskin.twitter.user.tweet.usecase.TweetEditUseCase;
import ru.aveskin.twitter.user.tweet.usecase.TweetFindUseCase;
import ru.aveskin.twitter.user.tweet.web.dto.*;

@RestController
@RequestMapping("/api/v1/tweets")
@RequiredArgsConstructor
public class TweetController {
    private final TweetAddUseCase tweetAddUseCase;
    private final TweetEditUseCase tweetEditUseCase;
    private final TweetDeleteUseCase tweetDeleteUseCase;
    private final TweetFindUseCase tweetFindUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponse addTweet(@Valid @RequestBody TweetAddRequest request) {
        return tweetAddUseCase.addTweet(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TweetResponse editTweet(@Valid @RequestBody TweetEditRequest request) {
        return tweetEditUseCase.editTweet(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTweet(@Valid @RequestBody TweetDeleteRequest request) {
        tweetDeleteUseCase.deleteTweet(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TweetPageResponse findOwnerTweets(@PathParam("page") int page,
                                             @PathParam("limit") int limit) {
        TweetFindRequest tweetFindRequest = new TweetFindRequest(page, limit);
        return tweetFindUseCase.findTweets(tweetFindRequest);
    }
}
