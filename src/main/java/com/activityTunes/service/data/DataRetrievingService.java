package com.activityTunes.service.data;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DataRetrievingService {

    private DataPersistingService dataPersistingService;

    public String getStravaAccessTokenByUuid(String uuid) {
        // TODO handle expired token and do a refresh?
        return dataPersistingService.data.get(uuid).get("strava").get("accessToken");
    }

    public String getSpotifyAccessTokenByUuid(String uuid) {
        // TODO handle expired token and do a refresh?
        return dataPersistingService.data.get(uuid).get("spotify").get("accessToken");
    }

    public String getUuidByStravaAthleteId(String athleteId) {
        // TODO handle expired token and do a refresh?
        return "jason";
    }
}
