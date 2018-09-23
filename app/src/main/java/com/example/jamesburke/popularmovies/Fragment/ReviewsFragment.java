package com.example.jamesburke.popularmovies.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
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

public class ReviewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{

    private static final String LOG_TAG = ReviewsFragment.class.getSimpleName();

    private MovieData myMovieData;
    ReviewAdapter mReviewAdapter;
    private ArrayList<MovieReviewsData> myMovieReviewsDataList;
    private RecyclerView rv;
    ChildActivity childActivity;


    public ReviewsFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstances){
        super.onActivityCreated(savedInstances);
        getLoaderManager().initLoader(0, null, this);
        getLoaderManager().getLoader(0).startLoading();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        childActivity = (ChildActivity) getActivity();
        myMovieData = childActivity.getMyData();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        rv = (RecyclerView) view.findViewById(R.id.reviews_recycler_view);

        rv.setLayoutManager(new LinearLayoutManager(childActivity));


        return view;
    }



    @NonNull
    public Loader<String> onCreateLoader(int id, @Nullable final Bundle args){
        return new AsyncTaskLoader<String>(getContext()) {

            String mReviewJson;


            @Override
            public void onStartLoading() {

                Log.i(LOG_TAG, "Loader startinggggggg");

                if (mReviewJson != null) {
                    deliverResult(mReviewJson);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String loadInBackground() {

                URL searchUrl = NetworkUtils.buildURL("reviews", myMovieData.getMyMovieId());
                String reviewSearchResults = null;
                try {
                    reviewSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                } catch (IOException e){
                    Log.e("ReviewsFragment", "Problem making the HTTP request.", e);
                }
                return reviewSearchResults;
            }

            @Override
            public void deliverResult(String movieReviewJson){
                mReviewJson = movieReviewJson;
                super.deliverResult(movieReviewJson);
            }
        };
    }


    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if(data == "") {
            rv.setVisibility(View.GONE);
            return;
        }else{
            rv.setVisibility(View.VISIBLE);
            myMovieReviewsDataList = JsonFragmentUtils.parseMovieReviewsData(data);
        }

        mReviewAdapter = new ReviewAdapter(getContext(), myMovieReviewsDataList);
        rv.setAdapter(mReviewAdapter);
    }

    public void onLoaderReset(@NonNull Loader<String> loader) {

    }



}
