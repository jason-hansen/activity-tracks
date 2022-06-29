package com.activityTunes.service.strava;

import com.activityTunes.service.strava.model.StravaAccessTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@Service
public class StravaRequestingService {

    String OAUTH_BASE_URL = "https://www.strava.com/oauth/token";
    String STRAVA_CLIENT_ID = Dotenv.configure().load().get("STRAVA_CLIENT_ID");
    String STRAVA_CLIENT_SECRET = Dotenv.configure().load().get("STRAVA_CLIENT_SECRET");

    public StravaAccessTokenResponse getAccessTokenFromAuthCode(String authCode) throws IOException, InterruptedException {
//        curl -X POST https://www.strava.com/oauth/token \
//        -F client_id=76589 \
//        -F client_secret=89fd781fd64f94f3914d987e41ea2ab510839b8c \
//        -F code=f13c59742a360732026b1c4071d29bec0e97fa08 \
//        -F grant_type=authorization_code

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
}
