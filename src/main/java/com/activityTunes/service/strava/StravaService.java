package com.activityTunes.service.strava;

import com.activityTunes.service.data.DataPersistingService;
import com.activityTunes.service.strava.model.StravaAccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StravaService {

    private final StravaRequestingService stravaRequestingService;
    private final DataPersistingService dataPersistingService;

    public void handleAuthCode(String authCode) throws IOException, InterruptedException {
        StravaAccessTokenResponse accessTokenResponse = stravaRequestingService.getAccessTokenFromAuthCode(authCode);
        dataPersistingService.persistStravaTokens(
                "jason",
                accessTokenResponse.getAthlete().getId(),
                accessTokenResponse.getAccessToken(),
                accessTokenResponse.getRefreshToken());
    }
}
