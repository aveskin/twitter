package ru.aveskin.twitter.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aveskin.twitter.security.model.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByAuthority(String authority);
}
