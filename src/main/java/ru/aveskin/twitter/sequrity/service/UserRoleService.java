package ru.aveskin.twitter.sequrity.service;

import ru.aveskin.twitter.sequrity.model.UserRole;

import java.util.Optional;

public interface UserRoleService {
    Optional<UserRole> findUserRole();
}
