package com.cosmin.tweets.converter;

import com.cosmin.tweets.model.Hashtag;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import twitter4j.HashtagEntity;

@Component
public class HashtagEntityToHashtagConverter implements Converter<HashtagEntity, Hashtag> {

    @Override
    public Hashtag convert(HashtagEntity source) {
        return Hashtag.builder()
                .id(source.getText())
                .count(1)
                .build();
    }
}
