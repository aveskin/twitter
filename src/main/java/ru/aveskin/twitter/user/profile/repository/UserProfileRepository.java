package ru.aveskin.twitter.user.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aveskin.twitter.user.profile.model.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByNickname(String nickname);

    Optional<UserProfile> findById(long id);
}
