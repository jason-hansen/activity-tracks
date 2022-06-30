package com.activityTunes.service.spotify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    private Album album;
    private ArrayList<Artist> artists;
    @JsonProperty("available_markets")
    private ArrayList<String> availableMarkets;
    private int disc_number;
    private int duration_ms;
    private boolean explicit;
    @JsonProperty("external_ids")
    private ExternalIds externalIds;
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    @JsonProperty("is_local")
    private boolean isLocal;
    private String name;
    private int popularity;
    @JsonProperty("preview_url")
    private String previewUrl;
    @JsonProperty("track_number")
    private int trackNumber;
    private String type;
    private String uri;
}
