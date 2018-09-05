package com.example.jamesburke.popularmovies.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamesburke.popularmovies.ChildActivity;
import com.example.jamesburke.popularmovies.R;
import com.example.jamesburke.popularmovies.utilities.MovieData;
import com.example.jamesburke.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */

//https://api.themoviedb.org/3/movie/299536/reviews?api_key=73e4a66f623745e5138464b4ac6fb93b
public class ReviewsFragment extends Fragment {

    private MovieData myMovieData;
    private obtainReviewTask mObtainReviewTask;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChildActivity childActivity = (ChildActivity) getActivity();
        myMovieData = childActivity.getMyData();
        String myTitle = myMovieData.getMyTitle();
        Log.v("ReviewsFragment", myTitle);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mObtainReviewTask = new obtainReviewTask();
        mObtainReviewTask.execute();

        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }

    public class obtainReviewTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            URL searchUrl = NetworkUtils.buildURL("reviews", myMovieData.getMyMovieId());;
            String reviewSearchResults = null;
           try {
                reviewSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e){
                Log.e("Main Activity", "Problem making the HTTP request.", e);
            }
            return reviewSearchResults;
        }
    }

}
