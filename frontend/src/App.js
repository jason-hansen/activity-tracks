import React from 'react';
import './App.css';
import logo from './assets/activity-tunes/activity_tunes_icon2.png';
import poweredByStrava from './assets/strava/api_logo_pwrdBy_strava_horiz_light.png';
import Buttons from './Buttons';

function App() {

  return (
    <div className="App">
      <img src={logo} className="App-logo" alt="logo" />
      <div className='title'>
        Activity Tracks
      </div>
      <div>
        Update your Strava activity's description with the tracks you listened to while you were out there. Automatically.
      </div>
      <Buttons />
      <div>
        <p>FAQ</p>
        <ul>
          <li>How does it work?</li>
          <li>What do I need to install?</li>
          <li>What data does Activity Tracks store?</li>
          <li>When do my tracks get loaded in?</li>
        </ul>
        <iframe
          src="https://ghbtns.com/github-btn.html?user=jason-hansen&type=follow&count=true&size=large"
          frameBorder={0}
          scrolling={0}
          width={230}
          height={30}
          title="GitHub">
        </iframe>
        <img
          src={poweredByStrava}
          alt="powered by strava logo"
          className='powered-by-strava-logo'
        />
      </div>
    </div>
  );
}

export default App;
