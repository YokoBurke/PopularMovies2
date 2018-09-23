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
import com.example.jamesburke.popularmovies.utilities.MovieVideosData;
import com.example.jamesburke.popularmovies.utilities.NetworkUtils;
import com.example.jamesburke.popularmovies.utilities.ReviewAdapter;
import com.example.jamesburke.popularmovies.utilities.VideosAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class VideosFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{

    private static final String LOG_TAG = VideosFragment.class.getSimpleName();

    private MovieData myMovieData;
    private VideosAdapter myVideosAdapter;
    private ArrayList<MovieVideosData> myMovieVideosDataList;

    private RecyclerView mRecyclerView;
    ChildActivity childActivity;

    public VideosFragment() {
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
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.videos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(childActivity));

        return view;
    }


    @NonNull
    public Loader<String> onCreateLoader(int id, @Nullable final Bundle args){
        return new AsyncTaskLoader<String>(getContext()) {

            String mVideoJson;

            @Override
            public void onStartLoading() {

                if (mVideoJson != null) {
                    deliverResult(mVideoJson);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String loadInBackground() {

                URL searchUrl = NetworkUtils.buildURL("videos", myMovieData.getMyMovieId());
                String videosSearchResults = null;
                try {
                    videosSearchResults  = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                } catch (IOException e){
                    Log.e("ReviewsFragment", "Problem making the HTTP request.", e);
                }
                return videosSearchResults;
            }

            @Override
            public void deliverResult(String movieVideosJson){
                mVideoJson = movieVideosJson;
                super.deliverResult(movieVideosJson);
            }
        };
    }


    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if(data == "") {
            mRecyclerView.setVisibility(View.GONE);
            return;
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
            myMovieVideosDataList = JsonFragmentUtils.parseMovieVideosData(data);
        }

        myVideosAdapter = new VideosAdapter(getContext(), myMovieVideosDataList, new VideosAdapter.ListItemClickListener(){
            @Override
            public void onListItemClick(int clickedItemIndex){
            }
        });
        mRecyclerView.setAdapter(myVideosAdapter);
    }

    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}