package com.activityTunes.service.spotify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
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
