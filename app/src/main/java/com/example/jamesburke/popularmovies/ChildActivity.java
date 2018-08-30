package com.example.jamesburke.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamesburke.popularmovies.data.AppDatabase;
import com.example.jamesburke.popularmovies.utilities.MovieData;
import com.squareup.picasso.Picasso;

public class ChildActivity extends AppCompatActivity {

    private ImageView mBackDrop;
    private ImageView mPoster;
    private TextView mTitle;
    private TextView mReleaseDate;
    private TextView mVoteAverage;
    private TextView mPlot;
    private ImageButton mStarIcon;

    private AppDatabase mDb;

    MovieData childMovieData;
    private int existanceCheck;
    private int checkTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mDb = AppDatabase.getsInstance(getApplicationContext());

        mBackDrop = (ImageView) findViewById(R.id.child_backdrop);
        mPoster = (ImageView) findViewById(R.id.child_poster);

        mTitle = (TextView) findViewById(R.id.child_title);
        mReleaseDate = (TextView) findViewById(R.id.child_release_date);
        mVoteAverage = (TextView) findViewById(R.id.child_vote_average);
        mPlot = (TextView) findViewById(R.id.child_plot);
        mStarIcon = (ImageButton) findViewById(R.id.favoritebutton);


        Intent childIntent = getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {
            childMovieData = (MovieData) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);

            Picasso.with(this).load(childMovieData.getMyPosterUrl()).into(mPoster);
            Picasso.with(this).load(childMovieData.getMyBDUrl()).into(mBackDrop);

            mTitle.setText(childMovieData.getMyTitle());
            mReleaseDate.setText(childMovieData.getMyReleaseDate());
            mVoteAverage.setText(Double.toString(childMovieData.getMyVoteAverage()));
            mPlot.setText(childMovieData.getMyOverview());
        }

        checkTable = childMovieData.getMyMovieId();
        Log.v("Child Activity", String.valueOf(checkTable) + "is the ID you are searching for.");
        existanceCheck = mDb.movieDao().findMovie(checkTable);
        Log.v("Child Activity", String.valueOf(existanceCheck) + "Checking if it exists in the table.");

        if (existanceCheck > 0) {
            mStarIcon.setImageResource(R.drawable.baseline_favorite_black_24);
        } else {
            mStarIcon.setImageResource(R.drawable.baseline_favorite_border_black_24);
        }


       mStarIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ChildActivity.this, "looks like on click listener is working", Toast.LENGTH_SHORT).show();
                onFavoriteButtonClicked();
            }
            });

    }

    public void onFavoriteButtonClicked() {

        if (existanceCheck == 0) {
            childMovieData.setMyFavorites();
            mDb.movieDao().insertMovie(childMovieData);
            mStarIcon.setImageResource(R.drawable.baseline_favorite_black_24);


            checkTable = childMovieData.getMyMovieId();
            Log.v("Child Activity2", String.valueOf(checkTable) + "is the ID you are searching for.");
            existanceCheck = mDb.movieDao().findMovie(checkTable);
            Log.v("Child Activity2", String.valueOf(existanceCheck) + "Checking if it exists in the table.");

        } else if (existanceCheck > 0) {
            childMovieData.unSetMyFavorites();
            mDb.movieDao().deleteMovie(childMovieData);
            mStarIcon.setImageResource(R.drawable.baseline_favorite_border_black_24);

        }

        //finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.childmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.child_favorite:
                Intent intent = new Intent(ChildActivity.this, FavoriteActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
