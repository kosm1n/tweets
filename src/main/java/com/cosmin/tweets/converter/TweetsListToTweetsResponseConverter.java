package com.cosmin.tweets.converter;

import com.cosmin.tweets.dto.TweetsResponse;
import com.cosmin.tweets.model.Tweet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TweetsListToTweetsResponseConverter implements Converter<List<Tweet>, TweetsResponse> {

    @Override
    public TweetsResponse convert(List<Tweet> source) {
        return TweetsResponse.builder()
                .tweets(source)
                .build();
    }
}
