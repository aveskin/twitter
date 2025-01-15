package ru.aveskin.twitter.user.tweet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aveskin.twitter.user.tweet.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
