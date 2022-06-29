package com.activityTunes.service.strava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Athlete {
    private int id;
    private String username;

    @JsonProperty("resource_state")
    private int resourceState;
    private String firstname;
    private String lastname;
    private String bio;
    private String city;
    private String state;
    private String country;
    private String sex;
    private boolean premium;
    private boolean summit;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("badge_type_id")
    private int badgeTypeId;
    private double weight;

    @JsonProperty("profile_medium")
    private String profileMedium;
    private String profile;
    private Object friend;
    private Object follower;
}

@Data
@AllArgsConstructor
@RequiredArgsConstructor
class Shoe {
    private String id;
    private boolean primary;
    private String name;
    @JsonProperty("resource_state")
    private int resourceState;
    private int distance;
}

@Data
@AllArgsConstructor
@RequiredArgsConstructor
class Bike {
    private String id;
    private boolean primary;
    private String name;
    @JsonProperty("resource_state")
    private int resourceState;
    private int distance;
}