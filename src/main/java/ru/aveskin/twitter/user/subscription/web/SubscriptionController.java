package ru.aveskin.twitter.user.subscription.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aveskin.twitter.user.subscription.usecase.SubscriptionAddUseCase;
import ru.aveskin.twitter.user.subscription.usecase.SubscriptionDeleteUseCase;
import ru.aveskin.twitter.user.subscription.web.dto.SubscribeRequest;
import ru.aveskin.twitter.user.subscription.web.dto.UnsubscribeRequest;


@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionAddUseCase subscriptionAddUseCase;
    private final SubscriptionDeleteUseCase subscriptionDeleteUseCase;

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

}
