package com.cosmin.tweets.repository;

import com.cosmin.tweets.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findAllByUserNameAndValidationTrue(String userName);

}
