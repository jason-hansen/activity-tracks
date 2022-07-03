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
    private final DataRetrievingService dataRetrievingService;

    public void handleAuthCode(String authCode) throws IOException, InterruptedException {
        SpotifyAccessTokenResponse accessTokenResponse = spotifyRequestingService.getAccessTokenFromAuthCode(authCode);
        int athleteId = dataRetrievingService.getAthleteId(); // TODO maybe I can pull this from the frontend somehow? as a dynamic query param given to spotify in the callback?
        dataPersistingService.persistSpotifyTokens(athleteId, accessTokenResponse.getAccessToken(), accessTokenResponse.getRefreshToken());
    }
}
