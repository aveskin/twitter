package ru.aveskin.twitter.user.subscription.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.aveskin.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.subscription.model.FollowerSubscription;
import ru.aveskin.twitter.user.subscription.service.SubscriptionService;
import ru.aveskin.twitter.user.subscription.usecase.SubscriptionFindFollowerUseCase;
import ru.aveskin.twitter.user.subscription.web.dto.FollowerFindRequest;
import ru.aveskin.twitter.user.subscription.web.dto.FollowerResponse;

import java.util.Collection;


@Component
@RequiredArgsConstructor
public class SubscriptionFindFollowerUseCaseFacade implements SubscriptionFindFollowerUseCase {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final SubscriptionService subscriptionService;

    @Override
    public Collection<FollowerResponse> findFollowers(FollowerFindRequest followerFindRequest) {
        UserProfile author = currentUserProfileApiService.currentUserProfile();

        Pageable pageable = PageRequest
                .of(followerFindRequest.page(),
                        followerFindRequest.limit()
//                        Sort.by(Sort.Direction.DESC, String.valueOf(Subscription_.createdTimestamp))
                );


        Page<FollowerSubscription> subscriptions = subscriptionService.findAllFollowerSubscriptions(author, pageable);

        return subscriptions.stream().map(s ->
                new FollowerResponse(
                        s.getId(),
                        s.getFollower().getId(),
                        s.getFollower().getNickname(),
                        s.getFollower().getImageLink(),
                        s.getCreatedTimestamp())
        ).toList();
    }
}
