package com.activityTunes.service.strava;

import com.activityTunes.controller.strava.model.WebhookData;
import com.activityTunes.service.data.DataPersistingService;
import com.activityTunes.service.data.DataRetrievingService;
import com.activityTunes.service.spotify.SpotifyRequestingService;
import com.activityTunes.service.spotify.model.SpotifyRecentlyPlayedResponse;
import com.activityTunes.service.spotify.model.Track;
import com.activityTunes.service.strava.model.DetailedActivity;
import com.activityTunes.service.strava.model.StravaAccessTokenResponse;
import com.activityTunes.service.track.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StravaService {

    private final StravaRequestingService stravaRequestingService;
    private final SpotifyRequestingService spotifyRequestingService;
    private final DataPersistingService dataPersistingService;
    private final DataRetrievingService dataRetrievingService;
    private final TrackService trackService;

    public String handleAuthCode(String authCode) throws IOException, InterruptedException {
        StravaAccessTokenResponse accessTokenResponse = stravaRequestingService.getAccessTokenFromAuthCode(authCode);
        dataPersistingService.persistStravaTokens(String.valueOf(accessTokenResponse.getAthlete().getId()), accessTokenResponse.getAccessToken(), accessTokenResponse.getRefreshToken());
        return String.valueOf(accessTokenResponse.getAthlete().getId());
    }

    public void handleWebhook(WebhookData webhookData) {
        // only process type ACTIVITY with aspect CREATE
        if (!webhookData.getObjectType().equalsIgnoreCase("activity")
                || !webhookData.getAspectType().equalsIgnoreCase("create")) {
            return;
        }

        log.info("Received webhook event: " + webhookData);

        String athleteId = String.valueOf(webhookData.getOwnerId());
        String activityId = String.valueOf(webhookData.getObjectId());

        try {
            String stravaAccessToken = dataRetrievingService.getStravaAccessTokenByAthleteId(athleteId);
            String spotifyAccessToken = dataRetrievingService.getSpotifyAccessTokenByAthleteId(athleteId);
            DetailedActivity detailedActivity = stravaRequestingService.getActivityById(stravaAccessToken, activityId);
            SpotifyRecentlyPlayedResponse recentlyPlayedResponse = spotifyRequestingService.getRecentlyPlayedTracks(spotifyAccessToken);
            List<Track> tracks = trackService.filterTracksPlayedDuringActivity(
                    recentlyPlayedResponse,
                    detailedActivity.getStart_date().getTime(),
                    computeEndTimeMillis(detailedActivity.getStart_date().getTime(), detailedActivity.getElapsed_time()));
            String newDescription = trackService.transformTracksToDescription(tracks);
            stravaRequestingService.updateActivityDescription(stravaAccessToken, detailedActivity, newDescription);
        } catch (IOException | InterruptedException e) {
            log.info("Wasn't able to handle the webhook correctly");
            e.printStackTrace();
        }
    }

    private long computeEndTimeMillis(long startTimeMillis, int elapsedTimeSeconds) {
        long endTimeMillis = startTimeMillis + (elapsedTimeSeconds * 1000L);
        log.info(String.format("Gathering tracks between %s and %s", new Date(startTimeMillis), new Date(endTimeMillis)));
        return endTimeMillis;
    }
}
