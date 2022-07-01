package com.activityTunes.service.strava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HighlightedKudoser {
    public String destination_url;
    public String display_name;
    public String avatar_url;
    public boolean show_name;
}
