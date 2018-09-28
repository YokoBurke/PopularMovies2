package com.example.jamesburke.popularmovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.jamesburke.popularmovies.data.AppDatabase;
import com.example.jamesburke.popularmovies.utilities.AppExecutors;
import com.example.jamesburke.popularmovies.utilities.FavoriteAdapter;
import com.example.jamesburke.popularmovies.utilities.MainViewModel;
import com.example.jamesburke.popularmovies.utilities.MovieData;

import java.util.ArrayList;
import java.util.List;

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

        android.support.v7.widget.Toolbar myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.favorite_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("My Favorite Movies");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.favorite_list);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FavoriteAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {

                AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<MovieData> movieData = mAdapter.getmMovieDatas();
                        mDb.movieDao().deleteThisMovie(movieData.get(position).getMyMovieId());
                    }
                });

            }
        }).attachToRecyclerView(mRecyclerView);

        mDb = AppDatabase.getsInstance(getApplicationContext());
        setUpViewModel();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void setUpViewModel(){

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getMovieData().observe(this, new Observer<List<MovieData>>() {
            @Override
            public void onChanged(@Nullable List<MovieData> movieData) {
                mAdapter.setMovieDatas(movieData);
            }

                });
    }



}
