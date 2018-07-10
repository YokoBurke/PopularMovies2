package com.example.jamesburke.popularmovies.utilities;

import java.io.Serializable;

public class MovieData implements Serializable {

    String myUrl;
    int myMovieId;

    public MovieData(String url, int movieID) {
        myUrl = url;
        myMovieId = movieID;
    }

    public String getMyUrl() {
        return myUrl;
    }

    public int getMyMovieId() {
        return myMovieId;
    }
}
