package com.activityTunes.controller.strava;

import com.activityTunes.controller.strava.model.WebhookData;
import com.activityTunes.service.strava.StravaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/strava")
public class StravaController {

    private final StravaService stravaService;

    @RequestMapping(value = {"/auth"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HttpHeaders> callback(@RequestParam Map<String, String> params) {
        String authCode = params.get("code");
        String athleteId = null;
        try {
            athleteId = stravaService.handleAuthCode(authCode);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:3000/strava?athlete_id=" + athleteId));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
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