package com.activityTunes.service.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    private AuthTokens stravaTokens;
    private AuthTokens spotifyTokens;
}
