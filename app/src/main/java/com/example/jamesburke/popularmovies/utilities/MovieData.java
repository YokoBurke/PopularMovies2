package com.example.jamesburke.popularmovies.utilities;

public class MovieData {

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
