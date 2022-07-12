import React from 'react';
import './App.css';
import logo from './assets/activity-tunes/activity_tunes_icon3.png';
import Buttons from './Buttons';
import Info from './Info';

function App() {

  return (
    <div className="App">
      <img src={logo} className="App-logo" alt="logo" />
      <div className='title'>
        Activity Tracks
      </div>
      <div className='subtitle'>
        Update your Strava activity's description with the tracks you listened to while you were out there. Automatically.
      </div>
      <Buttons />
      <Info />
    </div>
  );
}

export default App;
