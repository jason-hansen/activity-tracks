import React, { useState, useEffect } from 'react';

import './App.css';

import logo from './assets/activity-tunes/activity_tunes_icon2.png';
import strava from './assets/strava/btn_strava_connectwith_orange.svg';
import spotify from './assets/spotify/Spotify_Logo_RGB_White.png';
import spotify2 from './assets/spotify/Spotify_Logo_RGB_Black.png';

const BASE_API_URL = 'http://localhost:8081';

function App() {

  const [athleteId, setAthleteId] = useState('');

  useEffect(() => {
    if (!athleteId) {
      setAthleteId((new URLSearchParams(window.location.search)).get("athlete_id"));
    }
  });

  const requestStravaAuth = () => {
    window.location = 'https://www.strava.com/oauth/authorize?'
                    + '&client_id=76589'
                    + '&response_type=code'
                    + '&approval_prompt=force'
                    + '&scope=read,activity:read_all,activity:write'
                    + '&state=asdf'
                    + '&redirect_uri='+ BASE_API_URL +'/api/strava/auth';
  }

  const requestSpotifyAuth = () => {
    window.location = 'https://accounts.spotify.com/authorize?'
    + '&client_id=7225d09dece944b7879335138c2d458b'
    + '&response_type=code'
    + '&scope=user-read-email+user-read-recently-played+playlist-modify-public+playlist-modify-private+playlist-read-collaborative'
    + '&state=' + athleteId
    + '&redirect_uri='+ BASE_API_URL+ '/api/spotify/auth';
  }

  return (
    <div className="App">
        <img src={logo} className="App-logo" alt="logo" />
        <div className='title'>
          Activity Tracks
        </div>
        <div>
          Update your Strava activity's description with the tracks you listened to while you were out there. Automatically.
        </div>
        <div
          className='connect-with-strava'
          onClick={() => requestStravaAuth()}
        >
          <img
            src={strava}
            alt="strava"
            className='strava-logo'
          />
        </div>
         {athleteId && athleteId.length > 0
            ? <div
                className='connect-with-spotify'
                onClick={() => requestSpotifyAuth()}
              >
                {/* use flex for this? */}
                <span>Connect with</span>
                <img
                  src={spotify}
                  alt="spotify"
                  className='spotify-logo'
                />
              </div>
            : <div
                className='connect-with-spotify2'
              >
                {/* use flex for this? */}
                <span>Connect with</span>
                <img
                  src={spotify2}
                  alt="spotify"
                  className='spotify-logo'
                />
              </div>
          }
    </div>
  );
}

export default App;
