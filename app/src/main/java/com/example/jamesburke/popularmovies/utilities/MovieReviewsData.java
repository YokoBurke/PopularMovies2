package com.example.jamesburke.popularmovies.utilities;

/**
 * Created by jamesburke on 9/4/18.
 */

public class MovieReviewsData {

    private String myAuthor;
    private String myContent;

    public MovieReviewsData (String mAuthor, String mContent) {
        myAuthor = mAuthor;
        myContent = mContent;
    }

    public String getMyAuthor() {
        return myAuthor;
    }

    public String getMyContent() {
        return myContent;
    }
}
