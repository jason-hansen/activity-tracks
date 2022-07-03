package com.activityTunes.service.spotify;

import com.activityTunes.service.data.DataPersistingService;
import com.activityTunes.service.spotify.SpotifyRequestingService;
import com.activityTunes.service.spotify.SpotifyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SpotifyServiceTest {

    @InjectMocks
    private SpotifyService spotifyService;

    @Mock
    private SpotifyRequestingService spotifyRequestingServiceMock;

    @Mock
    private DataPersistingService dataPersistingServiceMock;

    @Test
    void testHandleAuthCode() {
        // GIVEN

        // WHEN

        // THEN
        assertNotNull(spotifyService);
        assertNotNull(spotifyRequestingServiceMock);
        assertNotNull(dataPersistingServiceMock);
    }
}