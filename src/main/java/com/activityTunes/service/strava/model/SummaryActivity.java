package com.activityTunes.service.strava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryActivity {
    private int resource_state;
    private Athlete athlete;
    private String name;
    private double distance;
    private int moving_time;
    private int elapsed_time;
    private double total_elevation_gain;
    private ActivityType type;
    private String sport_type;
    private int workout_type;
    private long id;
    private Date start_date;
    private Date start_date_local;
    private String timezone;
    private double utc_offset;
    private Object location_city;
    private Object location_state;
    private String location_country;
    private int achievement_count;
    private int kudos_count;
    private int comment_count;
    private int athlete_count;
    private int photo_count;
    private Map map;
    private boolean trainer;
    private boolean commute;
    private boolean manual;
    @JsonProperty("private")
    private boolean myprivate;
    private String visibility;
    private boolean flagged;
    private String gear_id;
    private ArrayList<Double> start_latlng;
    private ArrayList<Double> end_latlng;
    private double average_speed;
    private double max_speed;
    private double average_cadence;
    private boolean has_heartrate;
    private double average_heartrate;
    private double max_heartrate;
    private boolean heartrate_opt_out;
    private boolean display_hide_heartrate_option;
    private double elev_high;
    private double elev_low;
    private long upload_id;
    private String upload_id_str;
    private String external_id;
    private boolean from_accepted_tag;
    private int pr_count;
    private int total_photo_count;
    private boolean has_kudoed;
    private String description;
    private double calories;
    private Object perceived_exertion;
    private boolean prefer_perceived_exertion;
    private ArrayList<SegmentEffort> segment_efforts;
    private ArrayList<SplitsMetric> splits_metric;
    private ArrayList<SplitsStandard> splits_standard;
    private ArrayList<Lap> laps;
    private ArrayList<BestEffort> best_efforts;
    private Gear gear;
    private Photos photos;
    private ArrayList<StatsVisibility> stats_visibility;
    private boolean hide_from_home;
    private String device_name;
    private String embed_token;
    private SimilarActivities similar_activities;
    private ArrayList<Object> available_zones;
}

//public class Athlete {
//    private
//   int id;
//    private
//   int resource_state;
//}