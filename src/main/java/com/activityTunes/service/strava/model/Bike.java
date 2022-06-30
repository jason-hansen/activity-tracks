package com.activityTunes.service.strava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Bike {
    private String id;
    private boolean primary;
    private String name;
    @JsonProperty("resource_state")
    private int resourceState;
    private int distance;
}
