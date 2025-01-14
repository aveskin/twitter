package ru.aveskin.twitter.security.service;

import ru.aveskin.twitter.security.model.UserRole;

import java.util.Optional;

public interface UserRoleService {
    Optional<UserRole> findUserRole();
}
