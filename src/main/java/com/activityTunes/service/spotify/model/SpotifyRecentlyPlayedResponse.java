package com.activityTunes.service.spotify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SpotifyRecentlyPlayedResponse {

    public ArrayList<Item> items;
    public String next;
    public Cursors cursors;
    public int limit;
    public String href;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Item {
    public Track track;
    @JsonProperty("played_at")
    public Date playedAt;
    public Object context;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Track {
    public Album album;
    public ArrayList<Artist> artists;
    @JsonProperty("available_markets")
    public ArrayList<String> availableMarkets;
    public int disc_number;
    public int duration_ms;
    public boolean explicit;
    @JsonProperty("external_ids")
    public ExternalIds externalIds;
    @JsonProperty("external_urls")
    public ExternalUrls externalUrls;
    public String href;
    public String id;
    @JsonProperty("is_local")
    public boolean isLocal;
    public String name;
    public int popularity;
    @JsonProperty("preview_url")
    public String previewUrl;
    @JsonProperty("track_number")
    public int trackNumber;
    public String type;
    public String uri;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Album {
    public String album_type;
    public ArrayList<Artist> artists;
    @JsonProperty("available_markets")
    public ArrayList<String> availableMarkets;
    @JsonProperty("external_urls")
    public ExternalUrls externalUrls;
    public String href;
    public String id;
    public ArrayList<Image> images;
    public String name;
    @JsonProperty("release_date")
    public String releaseDate;
    @JsonProperty("release_date_precision")
    public String releaseDatePrecision;
    @JsonProperty("total_tracks")
    public int totalTracks;
    public String type;
    public String uri;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Artist {
    @JsonProperty("external_urls")
    public ExternalUrls externalUrls;
    public String href;
    public String id;
    public String name;
    public String type;
    public String uri;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Cursors {
    public String after;
    public String before;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ExternalIds {
    public String isrc;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ExternalUrls {
    public String spotify;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Image {
    public int height;
    public String url;
    public int width;
}
