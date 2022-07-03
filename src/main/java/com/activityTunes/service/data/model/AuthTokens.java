package com.activityTunes.service.data.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthTokens {
    private final String accessToken;
    private final String refreshToken;

    public boolean isEmpty() {
        return accessToken == null && refreshToken == null;
    }
}
