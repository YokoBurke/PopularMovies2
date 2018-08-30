package com.example.jamesburke.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jamesburke.popularmovies.utilities.FavoriteAdapter;
import com.example.jamesburke.popularmovies.utilities.MovieData;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private ArrayList<MovieData> favoriteMovieData;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mRecyclerView = (RecyclerView) findViewById(R.id.favorite_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FavoriteAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

    }
}
