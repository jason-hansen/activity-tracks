package com.activityTunes.controller.strava;

import com.activityTunes.controller.strava.model.WebhookData;
import com.activityTunes.service.strava.StravaService;
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
     *
     * http://localhost:8081/api/strava/webhook
     */
    @RequestMapping(value = {"/webhook"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> webhookCallback(@RequestParam WebhookData webhookData) {
        new Thread(() -> stravaService.handleWebhook(webhookData)).start();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

