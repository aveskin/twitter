package ru.aveskin.twitter.user.subscription.usecase;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.aveskin.twitter.user.subscription.web.dto.FollowerFindRequest;
import ru.aveskin.twitter.user.subscription.web.dto.FollowerResponse;

import java.util.Collection;

@Validated
public interface SubscriptionFindFollowerUseCase {

    Collection<FollowerResponse> findFollowers(@Valid FollowerFindRequest followerFindRequest);
}
