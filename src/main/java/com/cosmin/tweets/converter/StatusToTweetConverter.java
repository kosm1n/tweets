package com.cosmin.tweets.converter;

import com.cosmin.tweets.model.Tweet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import twitter4j.Status;

@Component
public class StatusToTweetConverter implements Converter<Status, Tweet> {

    @Override
    public Tweet convert(Status status) {
        return Tweet.builder()
                .id(status.getId())
                .userName(status.getUser().getScreenName())
                .text(status.getText())
                .location(status.getUser().getLocation())
                .validation(Boolean.FALSE)
                .build();
    }
}
