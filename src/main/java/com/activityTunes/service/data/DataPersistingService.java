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
        HashMap<String, String> userTokens = new HashMap<>();
        userTokens.put("accessToken", accessToken);
        userTokens.put("refreshToken", refreshToken);

        HashMap<String, HashMap<String, String>> spotify = new HashMap<>();
        spotify.put("spotify", userTokens);

        data.put(uuid, spotify);

        data.forEach((key, value) -> log.info(key + " " + value));
    }

    public void persistStravaTokens(String uuid, int athleteId, String accessToken, String refreshToken) {
        HashMap<String, String> userTokens = new HashMap<>();
        userTokens.put("athleteId", String.valueOf(athleteId));
        userTokens.put("accessToken", accessToken);
        userTokens.put("refreshToken", refreshToken);

        HashMap<String, HashMap<String, String>> strava = new HashMap<>();
        strava.put("strava", userTokens);

        data.put(uuid, strava);

        data.forEach((key, value) -> log.info(key + " " + value));
    }
}
