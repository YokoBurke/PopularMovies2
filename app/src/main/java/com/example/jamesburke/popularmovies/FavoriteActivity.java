package com.example.jamesburke.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jamesburke.popularmovies.data.AppDatabase;
import com.example.jamesburke.popularmovies.utilities.FavoriteAdapter;
import com.example.jamesburke.popularmovies.utilities.MovieData;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private static final String LOG_TAG = FavoriteActivity.class.getSimpleName();

    private ArrayList<MovieData> favoriteMovieData;

    private RecyclerView mRecyclerView;
    private FavoriteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        mRecyclerView = (RecyclerView) findViewById(R.id.favorite_list);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FavoriteAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        Log.v(LOG_TAG, "THISSSSSS " + Integer.toString(mAdapter.getItemCount()));

        mDb = AppDatabase.getsInstance(getApplicationContext());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.setMovieDatas(mDb.movieDao().loadAllMovie());

    }



}
