package ru.aveskin.twitter.user.subscription.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aveskin.twitter.user.subscription.usecase.SubscriptionAddUseCase;
import ru.aveskin.twitter.user.subscription.usecase.SubscriptionDeleteUseCase;
import ru.aveskin.twitter.user.subscription.usecase.SubscriptionFindFollowerUseCase;
import ru.aveskin.twitter.user.subscription.web.dto.FollowerFindRequest;
import ru.aveskin.twitter.user.subscription.web.dto.FollowerResponse;
import ru.aveskin.twitter.user.subscription.web.dto.SubscribeRequest;
import ru.aveskin.twitter.user.subscription.web.dto.UnsubscribeRequest;

import java.util.Collection;


@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionAddUseCase subscriptionAddUseCase;
    private final SubscriptionDeleteUseCase subscriptionDeleteUseCase;
    private final SubscriptionFindFollowerUseCase subscriptionFindFollowerUseCase;

    @PostMapping("/subscribe")
    @ResponseStatus(HttpStatus.CREATED)
    public void subscribe(@Valid @RequestBody SubscribeRequest subscribeRequest) {
        subscriptionAddUseCase.subscribe(subscribeRequest);
    }

    @PostMapping("/unsubscribe")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unsubscribe(@Valid @RequestBody UnsubscribeRequest unsubscribeRequest) {
        subscriptionDeleteUseCase.unsubscribe(unsubscribeRequest);
    }

    @GetMapping("/followers")
    public Collection<FollowerResponse> allFollowers(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        FollowerFindRequest followerFindRequest = new FollowerFindRequest(page, limit);

        return subscriptionFindFollowerUseCase.findFollowers(followerFindRequest);
    }

}
