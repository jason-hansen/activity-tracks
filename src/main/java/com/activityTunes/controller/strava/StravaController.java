package com.activityTunes.controller.strava;

import com.activityTunes.service.strava.StravaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/strava")
public class StravaController {

    private final StravaService stravaService;

    @RequestMapping(value = {"/auth"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> callback(@RequestParam Map<String, String> params) {
        String authCode = params.get("code");

        try {
            stravaService.handleAuthCode(authCode);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * https://developers.strava.com/docs/webhooks/
     */
    @RequestMapping(value = {"/webhook"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> webhookCallback(@RequestParam Map<String, String> params) {
//        {
//            "aspect_type": "update",
//                "event_time": 1516126040,
//                "object_id": 1360128428,
//                "object_type": "activity",
//                "owner_id": 134815,
//                "subscription_id": 120475,
//                "updates": {
//                    "title": "Messy"
//                }
//        }
        ObjectMapper objectMapper = new ObjectMapper();
        String objectType = params.get("object_type"); // either athlete or activity
        String objectId = params.get("object_id"); // either activity id or athlete id
        String aspectType = params.get("aspect_type"); // either create, update, or delete

        // - pass webhook data (strings and response location?) into core
        // - in core
        //      - send call to responding port to acknowledge the POST of each new event with a status code of 200 OK within two seconds (asynchronously)
        //      - send call to requesting port to get more activity data from strava?
        //      - send call to fetching port to get mapping of strava athlete id -> my user id -> spotify user id
        //      - send call to requesting port to request 20? 30? recently played songs from spotify
        //      - send all recent songs and activity timestamps to internal core service and figure out which songs were listened to
        //      - send call to requesting port to update the activity's description

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

