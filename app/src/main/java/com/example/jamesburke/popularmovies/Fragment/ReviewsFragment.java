package com.example.jamesburke.popularmovies.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamesburke.popularmovies.ChildActivity;
import com.example.jamesburke.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */

//https://api.themoviedb.org/3/movie/299536/reviews?api_key=73e4a66f623745e5138464b4ac6fb93b
public class ReviewsFragment extends Fragment {


    public ReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChildActivity childActivity = (ChildActivity) getActivity();
        String myTitle = childActivity.getMyData().getMyTitle();
        Log.v("ReviewsFragment", myTitle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }

}
