package com.activityTunes.service.strava.model;

import java.util.ArrayList;
import java.util.Date;

public class BestEffort {
    public Object id;
    public int resource_state;
    public String name;
    public Activity activity;
    public Athlete athlete;
    public int elapsed_time;
    public int moving_time;
    public Date start_date;
    public Date start_date_local;
    public int distance;
    public int start_index;
    public int end_index;
    public Object pr_rank;
    public ArrayList<Object> achievements;
}
