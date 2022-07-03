package com.activityTunes.service.data;

import com.activityTunes.service.data.model.AuthTokens;
import com.activityTunes.service.data.model.UserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class DataPersistingService {

    public final HashMap<String, UserAuth> data;

    public DataPersistingService() {
        data = new HashMap<>();
    }

    public void persistSpotifyTokens(String athleteId, String accessToken, String refreshToken) {
        AuthTokens spotifyAuthTokens = new AuthTokens(accessToken, refreshToken);
        UserAuth existingLoginsForUser = data.getOrDefault(athleteId, new UserAuth());
        existingLoginsForUser.setSpotifyTokens(spotifyAuthTokens);
        data.put(athleteId, existingLoginsForUser);

        data.forEach((key, value) -> log.info(key + " " + value));
    }

    public void persistStravaTokens(String athleteId, String accessToken, String refreshToken) {
        AuthTokens stravaAuthTokens = new AuthTokens(accessToken, refreshToken);
        UserAuth existingLoginsForUser = data.getOrDefault(athleteId, new UserAuth());
        existingLoginsForUser.setStravaTokens(stravaAuthTokens);
        data.put(athleteId, existingLoginsForUser);

        data.forEach((key, value) -> log.info(key + " " + value));
    }
}
