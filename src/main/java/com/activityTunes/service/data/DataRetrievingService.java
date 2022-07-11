package com.activityTunes.service.data;

import com.activityTunes.service.data.model.AuthTokens;
import com.activityTunes.service.data.model.UserAuth;
import com.activityTunes.service.spotify.SpotifyRequestingService;
import com.activityTunes.service.spotify.model.SpotifyAccessTokenRefreshResponse;
import com.activityTunes.service.strava.StravaRequestingService;
import com.activityTunes.service.strava.model.StravaAccessTokenRefreshResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class DataRetrievingService {

    private DataPersistingService dataPersistingService;
    private StravaRequestingService stravaRequestingService;
    private SpotifyRequestingService spotifyRequestingService;

    public String getStravaAccessTokenByAthleteId(String athleteId) throws Exception {
        AuthTokens stravaTokens = dataPersistingService.data
                .getOrDefault(athleteId, new UserAuth())
                .getStravaTokens();
        if (stravaTokens == null) {
            throw new Exception("No Strava authentication tokens stored for athlete id" + athleteId);
        }
        StravaAccessTokenRefreshResponse refreshResponse = getRefreshedStravaAccessToken(stravaTokens.getRefreshToken());
        dataPersistingService.persistStravaTokens(athleteId, refreshResponse.getAccessToken(), refreshResponse.getRefreshToken());
        return refreshResponse.getAccessToken();
    }

    public String getSpotifyAccessTokenByAthleteId(String athleteId) throws Exception {
        AuthTokens spotifyTokens = dataPersistingService.data
                .getOrDefault(athleteId, new UserAuth())
                .getSpotifyTokens();
        if (spotifyTokens == null) {
            throw new Exception("No Spotify authentication tokens stored for athlete id" + athleteId);
        }
        SpotifyAccessTokenRefreshResponse refreshResponse = getRefreshedSpotifyAccessToken(spotifyTokens.getRefreshToken());
        dataPersistingService.persistSpotifyTokens(athleteId, refreshResponse.getAccessToken(), spotifyTokens.getRefreshToken());
        return refreshResponse.getAccessToken();
    }

    public String getAthleteId() throws Exception {
        for (Map.Entry<String, UserAuth> userAuthEntry : dataPersistingService.data.entrySet()) {
            if (userAuthEntry.getValue().getSpotifyTokens() == null && !userAuthEntry.getValue().getStravaTokens().isEmpty()) {
                return userAuthEntry.getKey();
            }
        }
        throw new Exception("Athlete id not found");
    }

    private StravaAccessTokenRefreshResponse getRefreshedStravaAccessToken(String refreshToken) throws IOException, InterruptedException {
        return stravaRequestingService.getRefreshedAccessToken(refreshToken);
    }

    private SpotifyAccessTokenRefreshResponse getRefreshedSpotifyAccessToken(String refreshToken) throws IOException, InterruptedException {
        return spotifyRequestingService.getRefreshedAccessToken(refreshToken);
    }

}
