# ACTIVITY TUNES
**Automatically update your Strava activity's description with the Spotify songs you listened to**

*by Jason Hansen*

## HOW TO USE
- create an account or login
- authenticate with strava account and spotify account
- start running!

## TODO
- strava webhook & ngrok
- error handling in api calls?
- aws
  - go over React tutorial
    - design frontend
      - logo
      - login/create account
      - 2 auth buttons that don't link, but swap url then swap back when returns
      - switch to pause service
  - auth
  - tie together both login options
  - database and not just in memory
- token refreshing?

## ROADMAP
1. regular flow with spotify
2. 
3. other integrations beyond spotify?
   1. apple music
   2. apple podcasts, overcast, pocket casts, soundcloud, audible

## LOGIC
1. upload/create strava activity https://developers.strava.com/docs/webhooks/
2. spotify get request for recently played songs https://developer.spotify.com/documentation/web-api/reference/#/operations/get-recently-played
3. do math to calculate the ones that occurred during your activity (all the spotify.played_at that are > strava.event_time)
  1. the RecentlyPlayedResponse has PlayHistory[], and PlayHistory has a timestamp played_at
4. Strava PUT to update the activity description with songs listed out https://developers.strava.com/docs/reference/#api-Activities-updateActivityById

## RESOURCES
- https://old.reddit.com/r/Strava/comments/ue5xox/i_have_built_a_strava_add_on_that_renames_your/
- https://github.com/JeffreyCA/spotify-recently-played-readme
- https://developer.spotify.com/console/get-recently-played/

## QUESTIONS
- any way to toggle it?
- how to handle logins?
  
## DATA

![logo](src/assets/activity_tunes_icon.png)