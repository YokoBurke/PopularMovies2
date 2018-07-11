package com.example.jamesburke.popularmovies.utilities;

import java.io.Serializable;

public class MovieData implements Serializable {

    String myPosterUrl;
    int myMovieId;
    String myTitle;
    String myReleaseDate;
    Double myVoteAverage;
    String myOverview;
    String myBDUrl;

    public MovieData(String posterUrl, int movieID, String movieTitle, String movieReleaseDate,
                     Double movieVoteAverage, String movieOverview, String backdropUrl) {

        myPosterUrl = posterUrl;
        myMovieId = movieID;
        myTitle = movieTitle;
        myReleaseDate = movieReleaseDate;
        myVoteAverage = movieVoteAverage;
        myOverview = movieOverview;
        myBDUrl = backdropUrl;
    }

    public String getMyUrl() {
        return myPosterUrl;
    }

    public int getMyMovieId() {
        return myMovieId;
    }
}
