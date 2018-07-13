package com.example.jamesburke.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.utilities.MovieData;
import com.squareup.picasso.Picasso;

public class ChildActivity extends AppCompatActivity {

    private ImageView mBackDrop;
    private ImageView mPoster;
    private TextView mTitle;
    private TextView mReleaseDate;
    private TextView mVoteAverage;
    private TextView mPlot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mBackDrop = (ImageView) findViewById(R.id.child_backdrop);
        mPoster = (ImageView) findViewById(R.id.child_poster);

        mTitle = (TextView) findViewById(R.id.child_title);
        mReleaseDate = (TextView) findViewById(R.id.child_release_date);
        mVoteAverage = (TextView) findViewById(R.id.child_vote_average);
        mPlot = (TextView) findViewById(R.id.child_plot);



        Intent childIntent = getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {
            MovieData childMovieData = (MovieData) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);

            Picasso.with(this).load(childMovieData.getMyUrl()).into(mPoster);
            Picasso.with(this).load(childMovieData.getMyBDUrl()).into(mBackDrop);

            mTitle.setText(childMovieData.getMyTitle());
            mReleaseDate.setText(childMovieData.getMyReleaseDate());
            mVoteAverage.setText(Double.toString(childMovieData.getMyVoteAverage()));
            mPlot.setText(childMovieData.getMyOverview());
        }

    }
}
