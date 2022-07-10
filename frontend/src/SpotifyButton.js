import React, { useContext } from "react";
import UserContext from "./UserContext";
import './App.css';
import spotify from './assets/spotify/Spotify_Logo_RGB_White.png';

const SpotifyButton = () => {
    const { spotifyClick } = useContext(UserContext);

    const onClick = () => {
        spotifyClick();
    };

    return (
        <div
            className='connect-with-spotify'
            onClick={() => onClick()}
        >
            <span>Connect with</span>
            <img
                src={spotify}
                alt="spotify"
                className='spotify-logo'
            />
        </div>
    );
};

export default SpotifyButton;
