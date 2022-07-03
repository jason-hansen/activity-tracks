package com.activityTunes.service.data;

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

    public String getStravaAccessTokenByAthleteId(String athleteId) throws IOException, InterruptedException {
        String refreshToken = dataPersistingService.data
                .getOrDefault(athleteId, new UserAuth())
                .getStravaTokens()
                .getRefreshToken();
        StravaAccessTokenRefreshResponse refreshResponse = getRefreshedStravaAccessToken(refreshToken);
        dataPersistingService.persistStravaTokens(athleteId, refreshResponse.getAccessToken(), refreshResponse.getRefreshToken());
        return refreshResponse.getAccessToken();
    }

    public String getSpotifyAccessTokenByAthleteId(String athleteId) throws IOException, InterruptedException {
        String refreshToken = dataPersistingService.data
                .getOrDefault(athleteId, new UserAuth())
                .getSpotifyTokens()
                .getRefreshToken();
        SpotifyAccessTokenRefreshResponse refreshResponse = getRefreshedSpotifyAccessToken(refreshToken);
        dataPersistingService.persistSpotifyTokens(athleteId, refreshResponse.getAccessToken(), refreshToken);
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
