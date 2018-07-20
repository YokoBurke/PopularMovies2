package com.example.jamesburke.popularmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.utilities.JsonUtils;
import com.example.jamesburke.popularmovies.utilities.MovieAdapter;
import com.example.jamesburke.popularmovies.utilities.MovieData;
import com.example.jamesburke.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final int MOVIE_SEARCH_LOADER = 125;
    private String selectedData = "popular";

    private ArrayList<MovieData> myMovieData;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycle_view);
        mRecyclerView.setHasFixedSize(true);

        emptyView = (TextView) findViewById(R.id.empty_view);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getSupportLoaderManager().initLoader(MOVIE_SEARCH_LOADER, null, this);

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            String mMovieJson;

            @Override
            public void onStartLoading() {

                if (mMovieJson != null) {
                    deliverResult(mMovieJson);
                } else {
                    forceLoad();

                }
            }

            @Nullable
            @Override
            public String loadInBackground() {
                URL searchURL = NetworkUtils.buildURL(selectedData);
                String myString = "";
                try {
                    myString = NetworkUtils.getResponseFromHttpUrl(searchURL);


                } catch (IOException e){
                    Log.e("Main Activity", "Problem making the HTTP request.", e);
                }

                return  myString;
            }

            @Override
            public void deliverResult(String movieJson){
                mMovieJson = movieJson;
                super.deliverResult(movieJson);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        if (data == "") {

            mRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            return;
        } else {

            mRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            myMovieData = JsonUtils.parseMovieData(data);

        }
        mAdapter = new MovieAdapter(MainActivity.this, myMovieData, new MovieAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(int clickedItemIndex) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.mytitle:
                selectedData = "top_rate";
                Log.i("Main", selectedData);
                setTitle("Top Rated");
                getSupportLoaderManager().restartLoader(MOVIE_SEARCH_LOADER, null, this);
                return true;
            case R.id.popularity:
                selectedData = "popular";
                Log.i("Main", selectedData);
                setTitle("Popular");
                getSupportLoaderManager().restartLoader(MOVIE_SEARCH_LOADER, null, this);
                return true;
            case R.id.upcoming:
                selectedData = "upcoming";
                Log.i("Main", selectedData);
                setTitle("Upcoming");
                getSupportLoaderManager().restartLoader(MOVIE_SEARCH_LOADER, null, this);
                return true;
            case R.id.now_playing:
                selectedData = "now_playing";
                Log.i("Main", selectedData);
                setTitle("Now Playing");
                getSupportLoaderManager().restartLoader(MOVIE_SEARCH_LOADER, null, this);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}
