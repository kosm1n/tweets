package com.cosmin.tweets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetsRequest {

    @NotNull
    private Long tweetId;

    @NotNull
    private Boolean validation;

}
