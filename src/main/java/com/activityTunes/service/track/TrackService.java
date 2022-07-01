package com.activityTunes.service.track;

import com.activityTunes.service.spotify.model.Artist;
import com.activityTunes.service.spotify.model.SpotifyRecentlyPlayedResponse;
import com.activityTunes.service.spotify.model.Track;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrackService {

    public List<Track> filterTracksPlayedDuringActivity(SpotifyRecentlyPlayedResponse recentlyPlayedResponse, long activityStart, long activityEnd) {
        List<Track> tracks = new ArrayList<>();
        Date start = new Date(activityStart);
        Date end = new Date(activityEnd);

        recentlyPlayedResponse.getItems().forEach(item -> {
            if (item.getPlayedAt().after(start) && item.getPlayedAt().before(end)) {
                tracks.add(0, item.getTrack()); // instead of reversing it
            }
        });

        log.info(String.format("Filtered tracks from %s to %s", recentlyPlayedResponse.getItems().size(), tracks.size()));
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

        String newDescription = String.join("\n", data);
        log.info("Transformed tracks into new part of description: " + newDescription);
        return newDescription;
    }
}
