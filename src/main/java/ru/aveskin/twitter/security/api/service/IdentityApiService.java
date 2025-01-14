package ru.aveskin.twitter.security.api.service;

import ru.aveskin.twitter.security.api.model.CurrentUserApiModel;

import java.util.Optional;

public interface IdentityApiService {
    Optional<CurrentUserApiModel> currentUserAccount();
}
