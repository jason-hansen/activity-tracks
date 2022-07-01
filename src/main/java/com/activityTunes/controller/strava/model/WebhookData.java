package com.activityTunes.controller.strava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WebhookData {
    @JsonProperty("aspect_type")
    private String aspectType; // either create, update, or delete

    @JsonProperty("event_time")
    private Long eventTime;

    @JsonProperty("object_id")
    private Long objectId; // either activity id or athlete id

    @JsonProperty("object_type")
    private String objectType; // either athlete or activity

    @JsonProperty("owner_id")
    private Long ownerId;

    @JsonProperty("subscription_id")
    private Long subscriptionId;

    private Updates updates;
}

