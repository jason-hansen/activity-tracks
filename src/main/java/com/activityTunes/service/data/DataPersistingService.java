package com.activityTunes.service.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class DataPersistingService {

    public final HashMap<String, HashMap<String, HashMap<String, String>>> data;

    public DataPersistingService() {
        data = new HashMap<>();
    }

    public void persistSpotifyTokens(String uuid, String accessToken, String refreshToken) {
        HashMap<String, String> spotifyLogin = new HashMap<>();
        spotifyLogin.put("accessToken", accessToken);
        spotifyLogin.put("refreshToken", refreshToken);

        HashMap<String, HashMap<String, String>> existingLoginsForUser = data.getOrDefault(uuid, new HashMap<>());
        existingLoginsForUser.put("spotify", spotifyLogin);
        data.put(uuid, existingLoginsForUser);

        data.forEach((key, value) -> log.info(key + " " + value));
    }

    public void persistStravaTokens(String uuid, int athleteId, String accessToken, String refreshToken) {
        HashMap<String, String> stravaLogin = new HashMap<>();
        stravaLogin.put("athleteId", String.valueOf(athleteId));
        stravaLogin.put("accessToken", accessToken);
        stravaLogin.put("refreshToken", refreshToken);

        HashMap<String, HashMap<String, String>> existingLoginsForUser = data.getOrDefault(uuid, new HashMap<>());
        existingLoginsForUser.put("strava", stravaLogin);
        data.put(uuid, existingLoginsForUser);

        data.forEach((key, value) -> log.info(key + " " + value));
    }
}
