package com.activityTunes.service.spotify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cursors {
    public String after; // to use as key to find the next page of items.
    public String before;
}
