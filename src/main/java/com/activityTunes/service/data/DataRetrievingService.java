package com.activityTunes.service.data;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@AllArgsConstructor
public class DataRetrievingService {

    private DataPersistingService dataPersistingService;

    public String getStravaAccessTokenByUuid(String uuid) {
        // TODO handle expired token and do a refresh?
        return dataPersistingService.data
                .getOrDefault(uuid, new HashMap<>())
                .getOrDefault("strava", new HashMap<>())
                .getOrDefault("accessToken", "");
    }

    public String getSpotifyAccessTokenByUuid(String uuid) {
        // TODO handle expired token and do a refresh?
        return dataPersistingService.data
                .getOrDefault(uuid, new HashMap<>())
                .getOrDefault("spotify", new HashMap<>())
                .getOrDefault("accessToken", "");
    }

    public String getUuidByStravaAthleteId(String athleteId) {
        // TODO handle expired token and do a refresh?
        return "jason";
    }
}
