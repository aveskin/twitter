package ru.aveskin.twitter.user.tweet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aveskin.twitter.user.profile.model.UserProfile;
import ru.aveskin.twitter.user.tweet.model.Tweet;


public interface TweetRepository extends JpaRepository<Tweet, Long> {
    Page<Tweet> findAllByUserProfile(UserProfile userProfile, Pageable pageable);
}
