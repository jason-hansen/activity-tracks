package com.activityTunes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivityTunesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityTunesApplication.class, args);
//        StravaRequestingService stravaRequestingService = new StravaRequestingService();
//        StravaPersistingService stravaPersistingService = new StravaPersistingService();
//        SummaryActivity summaryActivity = null;
//        try {
//            String accessToken = "60109990645919a59c430a3b5b9067ed4c35d429";
//            summaryActivity = stravaRequestingService.getActivityById(accessToken, "7395267192");
//            stravaPersistingService.updateActivityDescription(accessToken, summaryActivity, "Song\nsong\nsong");
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(summaryActivity);
    }
}