package com.activityTunes.service.spotify;

import com.activityTunes.service.spotify.model.SpotifyAccessTokenResponse;
import com.activityTunes.service.spotify.model.SpotifyRecentlyPlayedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SpotifyRequestingService {

    public static final String SPOTIFY_REDIRECT_URL = Dotenv.configure().load().get("SPOTIFY_REDIRECT_URL");

    private static final String ACCOUNTS_BASE_URL = "https://accounts.spotify.com";
    private static final String API_BASE_URL = "https://api.spotify.com/v1";

    public static final String SPOTIFY_CLIENT_ID = Dotenv.configure().load().get("SPOTIFY_CLIENT_ID");
    public static final String SPOTIFY_CLIENT_SECRET = Dotenv.configure().load().get("SPOTIFY_CLIENT_SECRET");

    public SpotifyAccessTokenResponse getAccessTokenFromAuthCode(String authCode) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String data = "grant_type=authorization_code&code=" + authCode + "&redirect_uri=" + SPOTIFY_REDIRECT_URL;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ACCOUNTS_BASE_URL + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((SPOTIFY_CLIENT_ID + ":" + SPOTIFY_CLIENT_SECRET).getBytes()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), SpotifyAccessTokenResponse.class);
    }

    public SpotifyRecentlyPlayedResponse getRecentlyPlayed(String accessToken) throws IOException, InterruptedException {
        return getRecentlyPlayed(accessToken, 30, new Date().getTime());
    }

    public SpotifyRecentlyPlayedResponse getRecentlyPlayed(String accessToken, int count) throws IOException, InterruptedException {
        return getRecentlyPlayed(accessToken, count, new Date().getTime());
    }

    private SpotifyRecentlyPlayedResponse getRecentlyPlayed(String accessToken, int count, Long beforeMillis) throws IOException, InterruptedException {
//        curl --request GET \
//        --url https://api.spotify.com/v1/me/player/recently-played?limit=2 \
//        --header 'Authorization: Bearer <ACCESS_TOKEN>' \
//        --header 'Content-Type: application/json'

        ObjectMapper objectMapper = new ObjectMapper();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_BASE_URL + "/me/player/recently-played"))
                .GET()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .header("limit", String.valueOf(count))
                .header("before", String.valueOf(beforeMillis))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), SpotifyRecentlyPlayedResponse.class);
    }
}
