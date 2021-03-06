import React, { useContext } from "react";
import UserContext from "./UserContext";
import './App.css';
import strava from './assets/strava/btn_strava_connectwith_orange.svg';

const StravaButton = () => {
    const { stravaClick } = useContext(UserContext);

    const onClick = () => {
        stravaClick();
    };

    return (
        <div
            className='strava'
            onClick={() => onClick()}
        >
            <img
                src={strava}
                alt="strava logo"
                className='strava-logo'
            />
        </div>
    );
};

export default StravaButton;
