package com.activityTunes.service.spotify;

import com.activityTunes.service.data.DataPersistingService;
import com.activityTunes.service.data.DataRetrievingService;
import com.activityTunes.service.spotify.model.SpotifyAccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SpotifyService {

    private final SpotifyRequestingService spotifyRequestingService;
    private final DataPersistingService dataPersistingService;

    public void handleAuthCode(String authCode, String athleteId) throws Exception {
        SpotifyAccessTokenResponse accessTokenResponse = spotifyRequestingService.getAccessTokenFromAuthCode(authCode);
        dataPersistingService.persistSpotifyTokens(athleteId, accessTokenResponse.getAccessToken(), accessTokenResponse.getRefreshToken());
    }
}
