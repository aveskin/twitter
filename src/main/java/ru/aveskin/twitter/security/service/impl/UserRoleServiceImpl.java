package ru.aveskin.twitter.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.security.model.UserRole;
import ru.aveskin.twitter.security.repository.UserRoleRepository;
import ru.aveskin.twitter.security.service.UserRoleService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public Optional<UserRole> findUserRole() {
        return userRoleRepository.findByAuthority("ROLE_USER");
    }
}
