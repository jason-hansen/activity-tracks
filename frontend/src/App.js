import React, { useState } from 'react';

import './App.css';

import logo from './assets/activity-tunes/activity_tunes_icon2.png';
import strava from './assets/strava/btn_strava_connectwith_orange.svg';
import spotify from './assets/spotify/Spotify_Logo_RGB_White.png';

function App() {

  const [athleteId, setAthleteId] = useState('jason');

  // const BASE_URL = 'http://localhost:3000';
  const BASE_API_URL = 'http://localhost:8081';

  const requestStravaAuth = () => {
    setAthleteId('hansen'); // https://reactjs.org/docs/hooks-overview.html
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
    + '&state=callbackchecksthis'
    + '&redirect_uri='+ BASE_API_URL+ '/api/spotify/auth';
  }

  return (
    <div className="App">
        <img src={logo} className="App-logo" alt="logo" />
        <div className='title'>
          Activity Tracks
        </div>
        <div className=''>
          Update your Strava activity's description with the tracks you listened to while you were out there. Automatically.
        </div>
        <div className=''>
          Strava Athlete ID: {athleteId}
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
        <div
          className='connect-with-spotify'
          onClick={() => requestSpotifyAuth()}
        >
          {/* TODO use flex for this */}
          <span className=''>Connect with</span>
          <img
            src={spotify}
            alt="spotify"
            className='spotify-logo'
          />
        </div>
    </div>
  );
}

export default App;
