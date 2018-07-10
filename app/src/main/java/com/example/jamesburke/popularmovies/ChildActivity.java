package com.example.jamesburke.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.utilities.MovieData;

public class ChildActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mReleaseDate;
    private TextView mVoteAverage;
    private TextView mPlot;

    private ImageView mPoster;

    MovieData childMovieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mTitle = (TextView) findViewById(R.id.child_title);
        mReleaseDate = (TextView) findViewById(R.id.child_release_date);
        mVoteAverage = (TextView) findViewById(R.id.child_vote_average);
        mPlot = (TextView) findViewById(R.id.child_plot);

        mPoster = (ImageView) findViewById(R.id.child_poster);

        //https://stackoverflow.com/questions/13722040/passing-arraylistmyobject-between-multiple-activities

        Intent childIntent = getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {
            childMovieData = (MovieData) childIntent.getSerializableExtra(Intent.EXTRA_TEXT);
            Log.i("ChildActivity", "I made it!");
        }

    }
}
