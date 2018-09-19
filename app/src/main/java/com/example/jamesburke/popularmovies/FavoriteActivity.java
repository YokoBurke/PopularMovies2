package com.example.jamesburke.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.jamesburke.popularmovies.data.AppDatabase;
import com.example.jamesburke.popularmovies.utilities.AppExecutors;
import com.example.jamesburke.popularmovies.utilities.FavoriteAdapter;
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
                        retrieveMovies();
                    }
                });

            }
        }).attachToRecyclerView(mRecyclerView);

        mDb = AppDatabase.getsInstance(getApplicationContext());

    }

    @Override
    protected void onResume() {
        super.onResume();
        //mAdapter.setMovieDatas(mDb.movieDao().loadAllMovie());
        retrieveMovies();

    }

    private void retrieveMovies(){

        AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<MovieData> movieData = mDb.movieDao().loadAllMovie();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setMovieDatas(movieData);
                    }
                });
            }
        });

    }



}
