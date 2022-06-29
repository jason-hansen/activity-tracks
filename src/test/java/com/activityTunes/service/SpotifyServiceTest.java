package com.activityTunes.service;

import com.activityTunes.service.data.DataPersistingService;
import com.activityTunes.service.spotify.SpotifyRequestingService;
import com.activityTunes.service.spotify.SpotifyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class SpotifyServiceTest {

    private SpotifyService spotifyService;

    @Mock
    private SpotifyRequestingService spotifyRequestingServiceMock;

    @Mock
    private DataPersistingService dataPersistingServiceMock;

    @BeforeEach
    void setup() {
        spotifyService = new SpotifyService(spotifyRequestingServiceMock, dataPersistingServiceMock);
    }

    @Test
    void getAccessToken() throws IOException, InterruptedException {
        // GIVEN
        String authCode = "authCode";

        // WHEN
        spotifyService.handleAuthCode(authCode);

        // THEN
    }
}