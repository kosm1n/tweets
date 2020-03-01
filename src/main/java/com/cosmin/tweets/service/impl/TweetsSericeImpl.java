package com.cosmin.tweets.service.impl;

import com.cosmin.tweets.converter.HashtagEntityToHashtagConverter;
import com.cosmin.tweets.converter.StatusToTweetConverter;
import com.cosmin.tweets.converter.TweetsListToTweetsResponseConverter;
import com.cosmin.tweets.dto.TweetsRequest;
import com.cosmin.tweets.dto.TweetsResponse;
import com.cosmin.tweets.model.Hashtag;
import com.cosmin.tweets.model.Tweet;
import com.cosmin.tweets.repository.HashtagRepository;
import com.cosmin.tweets.repository.TweetRepository;
import com.cosmin.tweets.service.TweetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import twitter4j.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TweetsSericeImpl implements TweetsService {

    @Autowired
    private Twitter twitter;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private StatusToTweetConverter statusToTweetConverter;

    @Autowired
    private TweetsListToTweetsResponseConverter tweetsListToTweetsResponseConverter;

    @Autowired
    private HashtagEntityToHashtagConverter hashtagEntityToHashtagConverter;

    @Override
    public TweetsResponse getTweets() {
        return tweetsListToTweetsResponseConverter.convert(tweetRepository.findAll());
    }

    @Override
    public TweetsResponse getValidatedTweets(String userName) {
        return tweetsListToTweetsResponseConverter.convert(tweetRepository.findAllByUserNameAndValidationTrue(userName));
    }

    @Override
    public Page<Hashtag> getHashtagRanking(Integer rankingSize) {
        return hashtagRepository.findByOrderByCountDesc(PageRequest.of(0, rankingSize));
    }

    @Override
    public TweetsResponse updateTweet(TweetsRequest request) {
        Tweet tweet = tweetRepository.findById(request.getTweetId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Tweet with id '" + request.getTweetId() + "' not found"));
        tweet.setValidation(request.getValidation());
        return tweetsListToTweetsResponseConverter.convert(Arrays.asList(tweetRepository.save(tweet)));
    }

    @Override
    public TweetsResponse consumeTweets(String userId) {
        List<Tweet> tweetsConsumed = new ArrayList<>();
        try {
            // ElChiringuitoT
            Query query = new Query(userId);
            query.setSince("2020-02-20");
            query.setCount(100);
            QueryResult result;
            //do {
                result = twitter.search(query);
                tweetsConsumed = result.getTweets().stream().map(tweet -> {
                    Tweet tweetSaved = null;
                    if(tweet.getUser().getFollowersCount() > 1500 &&
                            (tweet.getLang().equals("es") || tweet.getLang().equals("it") || tweet.getLang().equals("fr"))) {
                        if (tweetRepository.findById(tweet.getId()).isEmpty()) {
                            tweetSaved = tweetRepository.save(statusToTweetConverter.convert(tweet));
                            saveHashtags(tweet);
                        }
                    }
                    return tweetSaved;
                }).filter(tweet -> tweet != null).collect(Collectors.toList());
            //} while ((query = result.nextQuery()) != null);

        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return tweetsListToTweetsResponseConverter.convert(tweetsConsumed);
    }

    private void saveHashtags(Status tweet) {
        Arrays.stream(tweet.getHashtagEntities())
                .map(hashtagEntity -> hashtagEntityToHashtagConverter.convert(hashtagEntity))
                .collect(Collectors.toList()).forEach(hashtag -> {
            Optional<Hashtag> optionalHashtag = hashtagRepository.findById(hashtag.getId());
            if (optionalHashtag.isPresent()) {
                optionalHashtag.get().incrementCount();
                hashtagRepository.save(optionalHashtag.get());
            } else {
                hashtagRepository.save(hashtag);
            }
        });
    }
}
