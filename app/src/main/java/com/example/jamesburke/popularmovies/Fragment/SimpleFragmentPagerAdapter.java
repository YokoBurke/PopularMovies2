package com.example.jamesburke.popularmovies.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DetailsFragment();
        } else if (position == 1){
            return new ReviewsFragment();
        } else {
            return new VideosFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
