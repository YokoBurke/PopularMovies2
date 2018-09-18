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

public class ReviewsFragment extends Fragment {

    private static final String LOG_TAG = ReviewsFragment.class.getSimpleName();

    private MovieData myMovieData;
    private obtainReviewTask mObtainReviewTask;
    ReviewAdapter mReviewAdapter;
    private ArrayList<MovieReviewsData> myMovieReviewsDataList;
    private RecyclerView rv;
    ChildActivity childActivity;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        childActivity = (ChildActivity) getActivity();
        myMovieData = childActivity.getMyData();
        String myTitle = myMovieData.getMyTitle();
        Log.v("ReviewsFragment", myTitle);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        rv = (RecyclerView) view.findViewById(R.id.reviews_recycler_view);

        rv.setLayoutManager(new LinearLayoutManager(childActivity));

        mObtainReviewTask = new obtainReviewTask();
        mObtainReviewTask.execute();

        return view;
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
            return reviewSearchResults;
        }

        @Override
        protected void onPostExecute(String myReviewSearchResult){
            if (myReviewSearchResult != null && !myReviewSearchResult.equals("")){
                myMovieReviewsDataList = JsonFragmentUtils.parseMovieReviewsData(myReviewSearchResult);
                Log.i(LOG_TAG, myReviewSearchResult);

                mReviewAdapter = new ReviewAdapter(getContext(), myMovieReviewsDataList );
                String x = Integer.toString(mReviewAdapter.getItemCount());

                rv.setAdapter(mReviewAdapter);

            }

        }
    }

}
