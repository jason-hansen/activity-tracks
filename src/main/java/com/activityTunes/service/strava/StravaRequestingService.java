package com.activityTunes.service.strava;

import com.activityTunes.service.strava.model.DetailedActivity;
import com.activityTunes.service.strava.model.StravaAccessTokenRefreshResponse;
import com.activityTunes.service.strava.model.StravaAccessTokenResponse;
import com.activityTunes.service.strava.model.UpdatableActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@Slf4j
@Service
public class StravaRequestingService {

    private static final String OAUTH_BASE_URL = "https://www.strava.com/oauth/token";
    private static final String API_BASE_URL = "https://www.strava.com/api/v3/";
    private static final String STRAVA_CLIENT_ID = Dotenv.configure().load().get("STRAVA_CLIENT_ID");
    private static final String STRAVA_CLIENT_SECRET = Dotenv.configure().load().get("STRAVA_CLIENT_SECRET");

    public StravaAccessTokenResponse getAccessTokenFromAuthCode(String authCode) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();

        HashMap<String, Object> bodyValues = new HashMap<>();
        bodyValues.put("client_id", STRAVA_CLIENT_ID);
        bodyValues.put("client_secret", STRAVA_CLIENT_SECRET);
        bodyValues.put("code", authCode);

        String requestBody = objectMapper.writeValueAsString(bodyValues);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OAUTH_BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), StravaAccessTokenResponse.class);
    }

    public StravaAccessTokenRefreshResponse getRefreshedAccessToken(String refreshToken) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();

        HashMap<String, Object> bodyValues = new HashMap<>();
        bodyValues.put("client_id", STRAVA_CLIENT_ID);
        bodyValues.put("client_secret", STRAVA_CLIENT_SECRET);
        bodyValues.put("grant_type", "refresh_token");
        bodyValues.put("refresh_token", refreshToken);

        String requestBody = objectMapper.writeValueAsString(bodyValues);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OAUTH_BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), StravaAccessTokenRefreshResponse.class);
    }

    public DetailedActivity getActivityById(String accessToken, String activityId) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_BASE_URL + "activities/" + activityId + "?include_all_efforts=false"))
                .GET()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        DetailedActivity detailedActivity = objectMapper.readValue(response.body(), DetailedActivity.class);
        log.info(String.format("Retrieved summary activity: %s", detailedActivity));
        return detailedActivity;
    }

    public void updateActivityDescription(String accessToken, DetailedActivity detailedActivity, String newDescription) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();

        UpdatableActivity updatableActivity = new UpdatableActivity(detailedActivity, newDescription);
        String requestBody = objectMapper.writeValueAsString(updatableActivity);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_BASE_URL + "activities/" + detailedActivity.getId()))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .build();

        log.info(String.format("Updated activity: %s with description: %s", detailedActivity.getId(), updatableActivity.getDescription()));
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
