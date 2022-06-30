package com.activityTunes.service.spotify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private String name;
    private String type;
    private String uri;
}
