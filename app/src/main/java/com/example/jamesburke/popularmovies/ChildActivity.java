package com.example.jamesburke.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.Fragment.SimpleFragmentPagerAdapter;
import com.example.jamesburke.popularmovies.utilities.MovieData;

public class ChildActivity extends AppCompatActivity {

    private TextView mTitle;

    private MovieData childMovieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent childIntent = getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {
            childMovieData = (MovieData) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);

        }



            /*Picasso.with(this).load(childMovieData.getMyPosterUrl()).into(mPoster);
            Picasso.with(this).load(childMovieData.getMyBDUrl()).into(mBackDrop);

            mTitle.setText(childMovieData.getMyTitle());
            mReleaseDate.setText(childMovieData.getMyReleaseDate());
            mVoteAverage.setText(Double.toString(childMovieData.getMyVoteAverage()));
            mPlot.setText(childMovieData.getMyOverview()); */


    }
    public MovieData getMyData() {
        //https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        return childMovieData;
    }


}
