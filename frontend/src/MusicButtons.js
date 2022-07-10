import React from "react";
import './App.css';
import SpotifyButton from "./SpotifyButton";
import AppleButton from "./AppleButton";

const MusicButtons = () => {

    return (
        <div className='music-box'>
            <SpotifyButton />
            <AppleButton />
        </div>
    );
};

export default MusicButtons;
