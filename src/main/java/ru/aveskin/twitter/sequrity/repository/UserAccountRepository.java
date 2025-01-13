package ru.aveskin.twitter.sequrity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aveskin.twitter.sequrity.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByUsername(String username);
}
