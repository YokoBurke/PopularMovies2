package com.example.jamesburke.popularmovies.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamesburke.popularmovies.ChildActivity;
import com.example.jamesburke.popularmovies.R;
import com.example.jamesburke.popularmovies.utilities.JsonFragmentUtils;
import com.example.jamesburke.popularmovies.utilities.MovieData;
import com.example.jamesburke.popularmovies.utilities.MovieReviewsData;
import com.example.jamesburke.popularmovies.utilities.NetworkUtils;
import com.example.jamesburke.popularmovies.utilities.ReviewAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

//https://api.themoviedb.org/3/movie/299536/reviews?api_key=73e4a66f623745e5138464b4ac6fb93b
public class ReviewsFragment extends Fragment {

    private MovieData myMovieData;
    private obtainReviewTask mObtainReviewTask;
    ReviewAdapter mReviewAdapter;
    private ArrayList<MovieReviewsData> myMovieReviewsDataList;

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

        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(mReviewAdapter);

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
                Log.e("ReviewsFragment", "Problem making the HTTP request.", e);
            }
            Log.i("ReviewFragment", reviewSearchResults);
            return reviewSearchResults;
        }

        @Override
        protected void onPostExecute(String myReviewSearchResult){
            if (myReviewSearchResult != null && !myReviewSearchResult.equals("")){
                myMovieReviewsDataList = JsonFragmentUtils.parseMovieReviewsData(myReviewSearchResult);
                Log.i("ReviewFragment222", myReviewSearchResult);
                mReviewAdapter = new ReviewAdapter(getContext(), myMovieReviewsDataList );

            }

        }
    }

}
