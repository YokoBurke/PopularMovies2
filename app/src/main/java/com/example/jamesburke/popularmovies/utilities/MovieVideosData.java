package com.example.jamesburke.popularmovies.utilities;

/**
 * Created by jamesburke on 9/4/18.
 */

public class MovieVideosData {

    private String mySiteKey;
    private String myTrailorName;
    private String myTrailorSite;

    private static final String myYoutubeURLBase = "https://www.youtube.com/watch?v=";
    private static final String myYoutubeImageURLBase = "https://img.youtube.com/vi/";

    public MovieVideosData (String mSiteKey, String mTrailorName, String mTrailorSite) {
        mySiteKey = mSiteKey;
        myTrailorName = mTrailorName;
        myTrailorSite = mTrailorSite;
    }

    public String getMyTrailorName(){
        return myTrailorName;
    }

    public String getMyTrailorSite() {
        return myTrailorSite;
    }

    public String returnYouTubeVideoURL() {

        return myYoutubeURLBase + mySiteKey;
    }

    public String returnYoutubeImageURL() {
        return myYoutubeImageURLBase + mySiteKey + "/default.jpg";
    }
}
