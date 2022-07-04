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
  - fix Spotify button style
  - add Apple Music button
  - info, FAQ, "Powered by Strava" graphic
  - what's the point of manifest.json?
- delete index.html in backend
- rename everything to activity tracks from activity tunes
- make frontend work well on mobile
- error handling in api calls?
- why spotify limit to 20 not accepting param of 50
- aws React tutorial https://aws.amazon.com/getting-started/hands-on/build-react-app-amplify-graphql/
- store athlete id and both auth tokens in a database
  - NoSql option?
- readme: structure, code flow, how it works/diagrams (include in readme and actual site?)
- handle when users revoke spotify/strava access
  - strava it comes over the webhook?
  - spotify??
- double check guidelines https://developers.strava.com/guidelines/ 
  - does Spotify have something similar?
- log4j.properties in /resources? logging colors? verbose config for DEBUG? etc.
- add link? to the bottom of each Strava description
- integrate with Apple Music