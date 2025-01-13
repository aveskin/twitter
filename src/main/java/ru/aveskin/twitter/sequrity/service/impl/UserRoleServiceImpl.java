package ru.aveskin.twitter.sequrity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.sequrity.model.UserRole;
import ru.aveskin.twitter.sequrity.repository.UserRoleRepository;
import ru.aveskin.twitter.sequrity.service.UserRoleService;

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
