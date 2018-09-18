package com.example.jamesburke.popularmovies.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.ChildActivity;
import com.example.jamesburke.popularmovies.R;
import com.example.jamesburke.popularmovies.data.AppDatabase;
import com.example.jamesburke.popularmovies.utilities.MovieData;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private static final String LOG_TAG = DetailsFragment.class.getSimpleName();
    private ImageView mBackDrop;
    private ImageView mPoster;
    private TextView mTitle;
    private TextView mReleaseDate;
    private TextView mVoteAverage;
    private TextView mPlot;
    private ImageButton mStarIcon;

    MovieData childMovieData;
    private boolean existanceCheck;

    private AppDatabase mDb;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mBackDrop = (ImageView) getView().findViewById(R.id.child_backdrop);
        mPoster = (ImageView) getView().findViewById(R.id.child_poster);

        mTitle = (TextView) getView().findViewById(R.id.child_title);
        mReleaseDate = (TextView) getView().findViewById(R.id.child_release_date);
        mVoteAverage = (TextView) getView().findViewById(R.id.child_vote_average);
        mPlot = (TextView) getView().findViewById(R.id.child_plot);

        mDb = AppDatabase.getsInstance(getContext().getApplicationContext());

        mStarIcon = (ImageButton) getView().findViewById(R.id.favoritebutton);
        mStarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });

        Context c = getActivity().getApplicationContext();

        ChildActivity childActivity = (ChildActivity) getActivity();
        childMovieData = childActivity.getMyData();

        if (childMovieData != null) {
            existanceCheck = searchDB(childMovieData.getMyMovieId());
            Log.i(LOG_TAG, "YEEES" + String.valueOf(existanceCheck));
            Picasso.with(c).load(childMovieData.getMyPosterUrl()).into(mPoster);
            Picasso.with(c).load(childMovieData.getMyBDUrl()).into(mBackDrop);

            mTitle.setText(childMovieData.getMyTitle());
            mReleaseDate.setText(childMovieData.getMyReleaseDate());
            mVoteAverage.setText(Double.toString(childMovieData.getMyVoteAverage()));
            mPlot.setText(childMovieData.getMyOverview());
        }  else {
            Log.v("DetailsFragment", "ChildActivity is null");
        }

        if(existanceCheck == true) {
            mStarIcon.setImageResource(R.drawable.baseline_favorite_black_24);
        } else {
            mStarIcon.setImageResource(R.drawable.baseline_favorite_border_black_24);
        }
    }

    public void onSaveButtonClicked() {
        Log.i(LOG_TAG, "Check again" + String.valueOf(existanceCheck));
        if(existanceCheck == true) {
            mStarIcon.setImageResource(R.drawable.baseline_favorite_border_black_24);
            Log.v(LOG_TAG, childMovieData.getMyTitle());
            mDb.movieDao().deleteThisMovie(childMovieData.getMyMovieId());
            existanceCheck = false;
        } else {
            mStarIcon.setImageResource(R.drawable.baseline_favorite_black_24);
            mDb.movieDao().insertMovie(childMovieData);
            existanceCheck = true;
        }

    }

    public boolean searchDB(int myMovieID) {
        Integer x = mDb.movieDao().checkExistance(myMovieID);
        if (x > 0){
            return true;
        } else{
            return false;
        }
    }


}
