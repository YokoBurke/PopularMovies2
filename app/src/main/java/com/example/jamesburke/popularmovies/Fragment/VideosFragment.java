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
import com.example.jamesburke.popularmovies.utilities.MovieVideosData;
import com.example.jamesburke.popularmovies.utilities.NetworkUtils;
import com.example.jamesburke.popularmovies.utilities.VideosAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {

    private static final String LOG_TAG = VideosFragment.class.getSimpleName();

    private MovieData myMovieData;
    private obtainVideosTask mObtainVideosTask;
    private VideosAdapter myVideosAdapter;
    private ArrayList<MovieVideosData> myMovieVideosDataList;

    private RecyclerView mRecyclerView;
    ChildActivity childActivity;

    public VideosFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        childActivity = (ChildActivity) getActivity();
        myMovieData = childActivity.getMyData();
        String myTitle = myMovieData.getMyTitle();
        Log.v(LOG_TAG, myTitle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.videos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(childActivity));

        mObtainVideosTask = new obtainVideosTask();
        mObtainVideosTask.execute();

        return view;
    }

    public class obtainVideosTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            URL searchUrl = NetworkUtils.buildURL("videos", myMovieData.getMyMovieId());
            ;
            String reviewSearchResults = null;
            try {
                reviewSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                Log.e("Main Activity", "Problem making the HTTP request.", e);
            }
            return reviewSearchResults;
        }

        @Override
        protected void onPostExecute(String myVideoSearchResult) {

            if (myVideoSearchResult != null && !myVideoSearchResult.equals("")) {
                myMovieVideosDataList = JsonFragmentUtils.parseMovieVideosData(myVideoSearchResult);
                Log.i(LOG_TAG, myVideoSearchResult);

                myVideosAdapter = new VideosAdapter(getContext(), myMovieVideosDataList);
                String y = Integer.toString(myVideosAdapter.getItemCount());
                mRecyclerView.setAdapter(myVideosAdapter);
            }
        }

    }
