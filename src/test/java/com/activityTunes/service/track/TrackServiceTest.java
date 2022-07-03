package com.activityTunes.service.track;

import com.activityTunes.service.spotify.model.Artist;
import com.activityTunes.service.spotify.model.Track;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackServiceTest {

    private final TrackService trackService = new TrackService();

    @Test
    void filterTracksPlayedDuringActivity() {
    }

    @Test
    void testTransformTracksToDescription() {
        // GIVEN
        Artist a1 = new Artist();
        a1.setName("Artist 1");
        Artist a2 = new Artist();
        a2.setName("Artist 2");

        Track t1 = new Track();
        t1.setName("Song 1");
        ArrayList<Artist> artists1 = new ArrayList<>();
        artists1.add(a1);
        t1.setArtists(artists1);

        Track t2 = new Track();
        t2.setName("Song 2");
        ArrayList<Artist> artists2 = new ArrayList<>();
        artists2.add(a1);
        artists2.add(a2);
        t2.setArtists(artists2);

        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(t1);
        tracks.add(t2);

        String expected = "Song 1 by Artist 1\nSong 2 by Artist 1, Artist 2";

        // WHEN
        String actual = trackService.transformTracksToDescription(tracks);

        // THEN
        assertEquals(expected, actual);
    }
}