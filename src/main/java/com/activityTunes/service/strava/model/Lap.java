package com.activityTunes.service.strava.model;

import java.util.Date;

public class Lap {
    public Object id;
    public int resource_state;
    public String name;
    public Activity activity;
    public Athlete athlete;
    public int elapsed_time;
    public int moving_time;
    public Date start_date;
    public Date start_date_local;
    public double distance;
    public int start_index;
    public int end_index;
    public double total_elevation_gain;
    public double average_speed;
    public double max_speed;
    public double average_cadence;
    public boolean device_watts;
    public double average_heartrate;
    public double max_heartrate;
    public int lap_index;
    public int split;
    public int pace_zone;
}
