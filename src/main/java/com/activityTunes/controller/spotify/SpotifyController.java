package com.activityTunes.controller.spotify;

import com.activityTunes.service.spotify.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/spotify")
public class SpotifyController {

    private final SpotifyService spotifyService;

    @RequestMapping(value = {"/auth"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HttpHeaders> callback(@RequestParam Map<String, String> params) {
        String code = params.get("code");
        String athleteId = params.get("state");
        try {
            spotifyService.handleAuthCode(code, athleteId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:3000"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
