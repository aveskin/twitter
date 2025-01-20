package ru.aveskin.twitter.user.subscription.mapper;

import ru.aveskin.twitter.security.mapper.Mapper;
import ru.aveskin.twitter.user.subscription.model.Subscription;
import ru.aveskin.twitter.user.subscription.web.dto.SubscribeRequest;

public interface SubscribeRequestToSubscriptionMapper extends Mapper<Subscription, SubscribeRequest> {
}
