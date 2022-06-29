package com.activityTunes.controller.spotify;

import com.activityTunes.service.spotify.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/spotify")
public class SpotifyController {

    private final SpotifyService spotifyService;

    @RequestMapping(value = {"/auth"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Boolean> callback(@RequestParam Map<String, String> params) {
        String code = params.get("code");
        String state = params.get("state");

        if (!state.equals("callbackchecksthis")) {
            return null;
        }

        try {
            spotifyService.handleAuthCode(code);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
