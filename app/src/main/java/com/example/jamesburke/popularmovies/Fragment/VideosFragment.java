package com.example.jamesburke.popularmovies.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class VideosFragment extends Fragment {

    private static final int MOVIE_SEARCH_LOADER = 127;
    private MovieData myMovieData;
    private obtainVideosTask mObtainVideosTask;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public VideosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChildActivity childActivity = (ChildActivity) getActivity();
        myMovieData = childActivity.getMyData();

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.videos_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mObtainVideosTask = new obtainVideosTask();
        mObtainVideosTask.execute();
        return inflater.inflate(R.layout.fragment_videos, container, false);

    }

    public class obtainVideosTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            URL searchUrl = NetworkUtils.buildURL("videos", myMovieData.getMyMovieId());;
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
