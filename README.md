# <img src="src/assets/activity_tunes_icon.png" width="30" /> ACTIVITY TUNES <img src="src/assets/activity_tunes_icon.png" width="30" />

**Automatically update your Strava activity's description with the Spotify songs you listened to**

### HOW TO USE
- login at: <site url here>
- authenticate with your Strava account 
- authenticate with your Spotify account
- start listening on Spotify while tracking your activities on Strava!
- the app will work its magic after each activity is uploaded to Strava

### TODO
- frontend
  - "success" checkbox when strava auth responds? or show "Hello _athlete name_"!
    - make ResponseEntity on callbacks have a body that the frontend can parse, not just url params?
    - store athlete id in state and don't just keep it in the url when it comes back from the HTTP 302
  - add logout button back in?
- rename everything to activity tracks from activity tunes
- store athlete id and both auth tokens in a database
  - NoSql option?
- deploy to AWS
- error handling in api calls
  - strava and spotify
    - when the user backs out of the auth login
    - when the user doesn't give the right permissions 
- why spotify limit to 20 not accepting param of 50'
- readme: structure, code flow, how it works/diagrams (include in readme and actual site?)
- handle when users revoke auth access
  - strava: it comes over the webhook?
  - spotify: ???
- double check guidelines https://developers.strava.com/guidelines/ 
  - does Spotify have something similar?
- log4j.properties in /resources? logging colors? verbose config for DEBUG? etc.
- integrate with Apple Music

### FUTURE FEATURE IDEAS
- the potential to make a playlist from an activity's songs
  - use activity id -> list of track ids
  - create some url link? to navigate to and create a playlist for a user
- an on/off switch to turn it off without revoking access?
- custom track/description formatting
- emojis by song with the fastest split
- emojis for song genres
- activity mile splits and song beats per minute analysis
- dark mode website
- email subscriber list