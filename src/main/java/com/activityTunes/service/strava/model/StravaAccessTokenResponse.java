package com.activityTunes.service.strava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StravaAccessTokenResponse {
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_at")
    private int expiresAt;

    @JsonProperty("expires_in")
    private int expiresIn; // 6 hours after it was created

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("access_token")
    private String accessToken;

    private Athlete athlete;
}
