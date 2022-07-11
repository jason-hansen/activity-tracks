package com.activityTunes.service.strava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdatableActivity {

    private boolean commute;
    private boolean trainer;
    private boolean hide_from_home;
    private String description;
    private ActivityType type;
    private String gear_id;

    public UpdatableActivity(DetailedActivity detailedActivity, String newDescription) {
        this.commute = detailedActivity.isCommute();
        this.trainer = detailedActivity.isTrainer();
        this.hide_from_home = detailedActivity.isHide_from_home();
        this.description = appendDescription(detailedActivity.getDescription(), newDescription);
        this.type = detailedActivity.getType();
        this.gear_id = detailedActivity.getGear_id();
    }

    public String appendDescription(String existingDescription, String newDescription) {
        if (existingDescription.isBlank()) {
            return newDescription;
        }
        return existingDescription + "\n" + newDescription;
    }
}
