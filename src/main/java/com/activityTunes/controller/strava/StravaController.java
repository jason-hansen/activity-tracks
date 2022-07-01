package com.activityTunes.controller.strava;

import com.activityTunes.controller.strava.model.WebhookData;
import com.activityTunes.service.strava.StravaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
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
     * a GET that returns the hub.challenge value to complete the handshake
     */
    @RequestMapping(value = {"/webhook"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> webhookAccepting(@RequestParam Map<String, String> params) {
        log.info("webhookAccepting: " + params);
        String response = String.format("{ \"%s\" : \"%s\" }", "hub.challenge", params.get("hub.challenge"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * POST that receives events from Strava
     */
    @RequestMapping(value = {"/webhook"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> webhookCallback(@RequestBody WebhookData webhookData) {
        new Thread(() -> stravaService.handleWebhook(webhookData)).start();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}