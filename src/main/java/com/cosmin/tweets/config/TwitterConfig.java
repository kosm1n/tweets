package com.cosmin.tweets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfig {

    @Autowired
    private TwitterConfigProperties properties;

    @Bean
    public Twitter client() {

        ConfigurationBuilder cb = new ConfigurationBuilder().setDebugEnabled(true)
                .setOAuthConsumerKey(properties.getConsumerKey())
                .setOAuthConsumerSecret(properties.getConsumerSecret())
                .setOAuthAccessToken(properties.getAccessToken())
                .setOAuthAccessTokenSecret(properties.getAccessTokenSecret());

        return new TwitterFactory(cb.build()).getInstance();
    }

}
