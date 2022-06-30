package com.activityTunes.service.strava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Segment {
    public int id;
    public int resource_state;
    public String name;
    public String activity_type;
    public double distance;
    public double average_grade;
    public double maximum_grade;
    public double elevation_high;
    public double elevation_low;
    public ArrayList<Double> start_latlng;
    public ArrayList<Double> end_latlng;
    public Object elevation_profile;
    public int climb_category;
    public String city;
    public String state;
    public String country;
    @JsonProperty("private")
    public boolean myprivate;
    public boolean hazardous;
    public boolean starred;
}
