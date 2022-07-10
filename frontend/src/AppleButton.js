import React, { useContext } from "react";
import UserContext from "./UserContext";
import './App.css';
import apple from './assets/apple/US-UK_Apple_Music_Listen_on_Lockup_RGB_wht_072720.svg'

const AppleButton = () => {
    const { appleClick } = useContext(UserContext);

    const onClick = () => {
        appleClick();
    };

    return (
        <div
            className='connect-with apple'
            onClick={() => onClick()}
        >
            <span className="">Connect with</span>
            <img
                src={apple}
                alt="apple logo"
                className='apple-logo'
            />
        </div>
    );
};

export default AppleButton;
