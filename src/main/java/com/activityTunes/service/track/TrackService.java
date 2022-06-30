package com.activityTunes.service.track;

import com.activityTunes.service.spotify.model.Artist;
import com.activityTunes.service.spotify.model.SpotifyRecentlyPlayedResponse;
import com.activityTunes.service.spotify.model.Track;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {

    public List<Track> filterTracksPlayedDuringActivity(SpotifyRecentlyPlayedResponse recentlyPlayedResponse, long activityStart, long activityEnd) {
        List<Track> tracks = new ArrayList<>();
        Date start = new Date(activityStart);
        Date end = new Date(activityEnd);

        recentlyPlayedResponse.getItems().forEach(item -> {
            if (item.getPlayedAt().after(start) && item.getPlayedAt().before(end)) {
                tracks.add(item.getTrack());
            }
        });
        return tracks;
    }

    public String transformTracksToDescription(List<Track> tracks) {
        List<String> data = new ArrayList<>();
        tracks.forEach(track -> {
            data.add(track.getName() + " by " + track.getArtists()
                                                        .stream()
                                                        .map(Artist::getName)
                                                        .collect(Collectors.joining(", ")));
        });
        return String.join("\n", data);
    }
}
