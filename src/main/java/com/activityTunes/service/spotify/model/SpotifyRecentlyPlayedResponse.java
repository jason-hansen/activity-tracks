package com.activityTunes.service.spotify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SpotifyRecentlyPlayedResponse {

    private ArrayList<Item> items;
    private String next;
    private Cursors cursors;
    private int limit;
    private String href;
}