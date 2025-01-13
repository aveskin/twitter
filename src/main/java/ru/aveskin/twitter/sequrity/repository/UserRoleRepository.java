package ru.aveskin.twitter.sequrity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aveskin.twitter.sequrity.model.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByAuthority(String authority);
}
