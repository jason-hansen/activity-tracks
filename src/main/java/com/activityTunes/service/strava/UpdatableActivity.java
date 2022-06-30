package com.activityTunes.service.strava;

import com.activityTunes.service.strava.model.SummaryActivity;
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

    public UpdatableActivity(SummaryActivity summaryActivity, String newDescription) {
        this.commute = summaryActivity.isCommute();
        this.trainer = summaryActivity.isTrainer();
        this.hide_from_home = summaryActivity.isHide_from_home();
        this.description = newDescription;
        this.type = summaryActivity.getType();
        this.gear_id = summaryActivity.getGear_id();
    }
}
