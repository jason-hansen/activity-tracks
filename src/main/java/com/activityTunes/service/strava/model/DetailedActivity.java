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
public class DetailedActivity {
    private long id;
    private int resource_state;
    private String external_id;
    private long upload_id;
    private Athlete athlete;
    private String name;
    private int distance;
    private int moving_time;
    private int elapsed_time;
    private int total_elevation_gain;
    private ActivityType type;
    private Date start_date;
    private Date start_date_local;
    private String timezone;
    private int utc_offset;
    private ArrayList<Double> start_latlng;
    private ArrayList<Double> end_latlng;
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
    private boolean flagged;
    private String gear_id;
    private boolean from_accepted_tag;
    private double average_speed;
    private double max_speed;
    private double average_cadence;
    private int average_temp;
    private double average_watts;
    private int weighted_average_watts;
    private double kilojoules;
    private boolean device_watts;
    private boolean has_heartrate;
    private int max_watts;
    private double elev_high;
    private double elev_low;
    private int pr_count;
    private int total_photo_count;
    private boolean has_kudoed;
    private int workout_type;
    private Object suffer_score;
    private String description;
    private double calories;
    private ArrayList<SegmentEffort> segment_efforts;
    private ArrayList<SplitsMetric> splits_metric;
    private ArrayList<Lap> laps;
    private Gear gear;
    private Object partner_brand_tag;
    private Photos photos;
    private ArrayList<HighlightedKudoser> highlighted_kudosers;
    private boolean hide_from_home;
    private String device_name;
    private String embed_token;
    private boolean segment_leaderboard_opt_out;
    private boolean leaderboard_opt_out;
}

