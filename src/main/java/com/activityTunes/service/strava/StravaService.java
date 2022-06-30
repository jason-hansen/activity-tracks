package com.activityTunes.service.strava;

import com.activityTunes.controller.strava.model.WebhookData;
import com.activityTunes.service.data.DataPersistingService;
import com.activityTunes.service.data.DataRetrievingService;
import com.activityTunes.service.spotify.SpotifyRequestingService;
import com.activityTunes.service.spotify.model.SpotifyRecentlyPlayedResponse;
import com.activityTunes.service.spotify.model.Track;
import com.activityTunes.service.strava.model.StravaAccessTokenResponse;
import com.activityTunes.service.strava.model.SummaryActivity;
import com.activityTunes.service.track.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public void handleAuthCode(String authCode) throws IOException, InterruptedException {
        StravaAccessTokenResponse accessTokenResponse = stravaRequestingService.getAccessTokenFromAuthCode(authCode);
        String uuid = dataRetrievingService.getUuidByStravaAthleteId(String.valueOf(accessTokenResponse.getAthlete().getId()));
        dataPersistingService.persistStravaTokens(uuid, accessTokenResponse.getAthlete().getId(), accessTokenResponse.getAccessToken(), accessTokenResponse.getRefreshToken());
    }

    public void handleWebhook(WebhookData webhookData) {
//        {
//            "aspect_type": "update",
//            "event_time": 1516126040,
//            "object_id": 1360128428,
//            "object_type": "activity",
//            "owner_id": 134815,
//            "subscription_id": 120475,
//            "updates": {
//                "title": "Messy"
//            }
//        }
        // TODO do some checking to make sure it's an activity upload

        String athleteId = String.valueOf(webhookData.getOwnerId());
        String activityId = String.valueOf(webhookData.getObjectId());

        try {
            String uuid = dataRetrievingService.getUuidByStravaAthleteId(athleteId);
            String stravaAccessToken = dataRetrievingService.getStravaAccessTokenByUuid(uuid);
            String spotifyAccessToken = dataRetrievingService.getSpotifyAccessTokenByUuid(uuid);
            SummaryActivity summaryActivity = stravaRequestingService.getActivityById(stravaAccessToken, activityId);
            SpotifyRecentlyPlayedResponse recentlyPlayedResponse = spotifyRequestingService.getRecentlyPlayedTracks(spotifyAccessToken);
            List<Track> tracks = trackService.filterTracksPlayedDuringActivity(
                    recentlyPlayedResponse,
                    summaryActivity.getStart_date_local().getTime(),
                    computeEndTimeMillis(summaryActivity.getStart_date_local().getTime(), summaryActivity.getElapsed_time()));
            String newDescription = trackService.transformTracksToDescription(tracks);
            stravaRequestingService.updateActivityDescription(stravaAccessToken, summaryActivity, newDescription);
        } catch (IOException | InterruptedException e) {
            log.info("Wasn't able to handle the webhook correctly");
            e.printStackTrace();
        }
    }

    private long computeEndTimeMillis(long startTimeMillis, int elapsedTimeSeconds) {
        return startTimeMillis + (elapsedTimeSeconds * 1000L);
    }
}
