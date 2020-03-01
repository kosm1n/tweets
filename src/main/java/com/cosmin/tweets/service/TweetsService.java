package com.cosmin.tweets.service;

import com.cosmin.tweets.dto.TweetsRequest;
import com.cosmin.tweets.dto.TweetsResponse;
import com.cosmin.tweets.model.Hashtag;
import org.springframework.data.domain.Page;

public interface TweetsService {

    TweetsResponse getTweets();

    TweetsResponse getValidatedTweets(String userName);

    TweetsResponse updateTweet(TweetsRequest request);

    TweetsResponse consumeTweets(String userId);

    Page<Hashtag> getHashtagRanking(Integer rankingSize);
}
