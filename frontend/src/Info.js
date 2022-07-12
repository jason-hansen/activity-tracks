import React from "react";
import './Info.css';
import poweredByStrava from './assets/strava/api_logo_pwrdBy_strava_horiz_light.png';

const Info = () => {
    return (
        <div>
            <div className='info-container'>
                <div className='row'>
                    <div className='column'>
                        <div className="question-container">
                            <div className="question-title">
                                How does it work?
                            </div>
                            <div className="answer-container">
                                When you create and upload an activity to Strava, Activity Tracks goes and grabs the songs you listened to.
                                Then it tells Strava to update your activity's description.
                            </div>
                        </div>
                        <div className="question-container">
                            <div className="question-title">
                                What do I need to install?
                            </div>
                            <div className="answer-container">
                                Nothing! Just authenticate your Strava and music account. Everything else happens in the cloud.
                            </div>
                        </div>
                    </div>
                    <div className='column'>
                        <div className="question-container">
                            <div className="question-title">
                                What data does Activity Tracks store?
                            </div>
                            <div className="answer-container">
                                We only store non-identifying authenication information to communicate with Strava and music services.
                            </div>
                        </div>
                        <div className="question-container">
                            <div className="question-title">
                                When do my tracks get loaded in?
                            </div>
                            <div className="answer-container">
                                As soon as you save an activity. It may take a minute as we contact your music service, so refresh in a minute if you don't see it immediately.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="footer">
                {/* <div>
                    <iframe
                        src="https://ghbtns.com/github-btn.html?user=jason-hansen&type=follow&count=true&size=large"
                        frameBorder={0}
                        scrolling={0}
                        width={230}
                        height={30}
                        title="GitHub">
                    </iframe>
                </div> */}
                <div>
                    <img
                        src={poweredByStrava}
                        alt="powered by strava logo"
                        className='powered-by-strava-logo'
                    />
                </div>
            </div>
        </div>
    )
}

export default Info;