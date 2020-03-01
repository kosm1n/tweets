package com.cosmin.tweets.controller;

import com.cosmin.tweets.dto.TweetsRequest;
import com.cosmin.tweets.dto.TweetsResponse;
import com.cosmin.tweets.model.Hashtag;
import com.cosmin.tweets.service.TweetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.Twitter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class TweetsController {

    @Autowired
    private Twitter twitter;

    @Autowired
    private TweetsService service;

    @PatchMapping(value = "/tweets")
    public ResponseEntity<TweetsResponse> updateTweet(@Valid @RequestBody TweetsRequest request) {
        return ResponseEntity.ok().body(service.updateTweet(request));
    }

    @GetMapping(value = "/tweets")
    public ResponseEntity<TweetsResponse> getTweets() {
        return ResponseEntity.ok().body(service.getTweets());
    }

    @GetMapping(value = "/tweets/validated")
    public ResponseEntity<TweetsResponse> getValidatedTweets(@Valid @RequestParam(name = "userName") @NotBlank String userName) {
        return ResponseEntity.ok().body(service.getValidatedTweets(userName));
    }

    @GetMapping(value = "/tweets/hashtags")
    public ResponseEntity<Page<Hashtag>> getHashtagRanking(@RequestParam(defaultValue="10", required = false, name = "rankingSize") Integer rankingSize) {
        return ResponseEntity.ok().body(service.getHashtagRanking(rankingSize));
    }

    @GetMapping(value = "/tweets/consume")
    public ResponseEntity<TweetsResponse> consumeUserTweets(@Valid @RequestParam(name = "userName") @NotBlank String userName) {
        return ResponseEntity.ok().body(service.consumeTweets(userName));
    }

}
