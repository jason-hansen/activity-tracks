package com.activityTunes.service.track;

import com.activityTunes.service.spotify.model.Artist;
import com.activityTunes.service.spotify.model.Item;
import com.activityTunes.service.spotify.model.SpotifyRecentlyPlayedResponse;
import com.activityTunes.service.spotify.model.Track;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackServiceTest {

    private final TrackService trackService = new TrackService();

    @Test
    void filterTracksPlayedDuringActivity() {
        // GIVEN
        long activityStart = 1656856800L; // 2:00
        long activityEnd = 1656860400L; // 3:00

        List<Track> tracks = createTestTracks();

        Item item0 = new Item();
        item0.setPlayedAt(new Date(1656860280)); // 2:58
        item0.setTrack(tracks.get(0));

        Item item1 = new Item();
        item1.setPlayedAt(new Date(1656860340L)); // 2:59
        item1.setTrack(tracks.get(1));

        Item item2 = new Item();
        item2.setPlayedAt(new Date(1656860460L)); // 3:01
        item2.setTrack(tracks.get(2));

        ArrayList<Item> items = new ArrayList<>();
        items.add(item0);
        items.add(item1);
        items.add(item2);

        SpotifyRecentlyPlayedResponse recentlyPlayedResponse = new SpotifyRecentlyPlayedResponse();
        recentlyPlayedResponse.setItems(items);

        List<Track> expected = List.of(tracks.get(0), tracks.get(1));

        // WHEN
        List<Track> actual = trackService.filterTracksPlayedDuringActivity(recentlyPlayedResponse, activityStart, activityEnd);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void testTransformTracksToDescription() {
        // GIVEN
        List<Track> tracks = createTestTracks();
        String expected = "\n\n~ Activity Tracks ~\n- Song 1 by Artist 1\n- Song 2 by Artist 1, Artist 2\n- Song 3 by Artist 2";

        // WHEN
        String actual = trackService.transformTracksToDescription(tracks);

        // THEN
        assertEquals(expected, actual);
    }

    private List<Track> createTestTracks() {
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

        Track t3 = new Track();
        t3.setName("Song 3");
        ArrayList<Artist> artists3 = new ArrayList<>();
        artists3.add(a2);
        t3.setArtists(artists3);

        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(t1);
        tracks.add(t2);
        tracks.add(t3);

        return tracks;
    }
}