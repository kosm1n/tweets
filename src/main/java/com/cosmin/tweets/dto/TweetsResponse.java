package com.cosmin.tweets.dto;

import com.cosmin.tweets.model.Tweet;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TweetsResponse {

    private List<Tweet> tweets;

}
