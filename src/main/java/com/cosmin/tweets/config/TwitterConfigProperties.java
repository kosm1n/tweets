package com.cosmin.tweets.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "com.cosmin.tweets.config.oauth")
public class TwitterConfigProperties {

    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

}
