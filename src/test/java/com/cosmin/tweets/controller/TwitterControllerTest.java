package com.cosmin.tweets.controller;

import com.cosmin.tweets.dto.ErrorResponse;
import com.cosmin.tweets.dto.TweetsRequest;
import com.cosmin.tweets.dto.TweetsResponse;
import com.cosmin.tweets.service.TweetsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TwitterControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TweetsController controller;

    @InjectMocks
    protected ObjectMapper objectMapper;

    @Mock
    private TweetsService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void updateTweetBadRequestTest() throws Exception {

        // act
        ResultActions resultActions = mockMvc.perform(
                patch("/tweets")
                        .content((String) "{}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // assert
        resultActions.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ErrorResponse response = objectMapper.readValue(contentAsString, ErrorResponse.class);
        assertEquals("Status should be: 400 BAD REQUEST.", Integer.valueOf(HttpStatus.BAD_REQUEST.value()), response.getStatus());
        assertTrue("Message should contain: ", response.getMessage().contains("tweetId must not be null"));
        assertTrue("Message should contain: ", response.getMessage().contains("validation must not be null"));

    }

    @Test
    public void updateTweetOkTest() throws Exception {

        // arrange
        TweetsRequest request = TweetsRequest.builder().tweetId(1231685019036504065L).validation(Boolean.TRUE).build();
        Mockito.doReturn(TweetsResponse.builder().tweets(new ArrayList<>()).build())
                .when(this.service)
                .updateTweet(any(TweetsRequest.class));

        // act
        ResultActions resultActions = mockMvc.perform(
                patch("/tweets")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // assert
        resultActions.andExpect(status().is(HttpStatus.OK.value()));

    }

    @Test
    public void getTweetsOkTest() throws Exception {

        // arrange
        Mockito.doReturn(TweetsResponse.builder().tweets(new ArrayList<>()).build())
                .when(this.service)
                .getTweets();

        // act
        ResultActions resultActions = mockMvc.perform(
                get("/tweets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // assert
        resultActions.andExpect(status().is(HttpStatus.OK.value()));

    }

    @Test
    public void getValidatedTweetsOkTest() throws Exception {

        // arrange
        Mockito.doReturn(TweetsResponse.builder().tweets(new ArrayList<>()).build())
                .when(this.service)
                .getValidatedTweets(any(String.class));

        // act
        ResultActions resultActions = mockMvc.perform(
                get("/tweets/validated?userName=realmadrid")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // assert
        resultActions.andExpect(status().is(HttpStatus.OK.value()));

    }

    @Test
    public void getValidatedTweetsBadRequestTest() throws Exception {

        // act
        ResultActions resultActions = mockMvc.perform(
                get("/tweets/validated")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // assert
        resultActions.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }

    @Test
    public void getHashtagRankingOkTest() throws Exception {

        // arrange
        Mockito.doReturn(TweetsResponse.builder().tweets(new ArrayList<>()).build())
                .when(this.service)
                .getValidatedTweets(any(String.class));

        // act
        ResultActions resultActions = mockMvc.perform(
                get("/tweets/hashtags?rankingSize=5")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // assert
        resultActions.andExpect(status().is(HttpStatus.OK.value()));

    }

    @Test
    public void getHashtagRankingDefaultSizeTest() throws Exception {

        // act
        ResultActions resultActions = mockMvc.perform(
                get("/tweets/hashtags")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // assert
        resultActions.andExpect(status().is(HttpStatus.OK.value()));

    }

    @Test
    public void consumeUserTweetsOkTest() throws Exception {

        // arrange
        Mockito.doReturn(TweetsResponse.builder().tweets(new ArrayList<>()).build())
                .when(this.service)
                .getValidatedTweets(any(String.class));

        // act
        ResultActions resultActions = mockMvc.perform(
                get("/tweets/consume?userName=realmadrid")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // assert
        resultActions.andExpect(status().is(HttpStatus.OK.value()));

    }

    @Test
    public void consumeUserTweetsBadRequestTest() throws Exception {

        // act
        ResultActions resultActions = mockMvc.perform(
                get("/tweets/consume")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // assert
        resultActions.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }
}
