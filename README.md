# <img src="src/assets/activity_tunes_icon.png" width="30" /> ACTIVITY TUNES <img src="src/assets/activity_tunes_icon.png" width="30" />

**Automatically update your Strava activity's description with the Spotify songs you listened to**

### HOW TO USE
- login at: <site url here>
- authenticate with your Strava account 
- authenticate with your Spotify account
- start listening on Spotify while tracking your activities on Strava!
- the app will work its magic after each activity is uploaded to Strava

### TODO
- get consistent with athleteId parsing
- unit tests! at least for TrackService.java
- error handling in api calls?
- why spotify limit to 20 not accepting param of 50
- how to actually store user id logins
  - force strava auth first, then spotify auth
    - have athlete id be the root of the storage
    - via webhook, use owner_id (athlete id) to get access tokens
- aws
  - go over React tutorial https://aws.amazon.com/getting-started/hands-on/build-react-app-amplify-graphql/module-three/
    - design frontend
      - login page
        - login/create account
      - logged in page
        - 2 auth buttons that don't link, but swap url then swap back when returns
        - switch to pause service
      - about page
  - tie together both login options
    - mabye just have users auth with stava first and then I'll store a map of athlete id -> strava stuff & spotify stuff
  - database and not just in memory
  - auth via aws?
- log4j.properties in /resources? logging colors? verbose config for DEBUG? etc.
- readme: structure, code flow, how it works/diagrams (include in readme and actual site?)
- handle when users revoke spotify/strava access
  - strava it comes over the webhook?
  - spotify??
- integrate with Apple Music