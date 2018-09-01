package com.example.jamesburke.popularmovies.Fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jamesburke.popularmovies.R;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
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

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.fragment_detail);
        } else if (position == 1) {
            return mContext.getString(R.string.fragment_reviews);
        } else {
            return mContext.getString(R.string.fragment_videos);
        }
    }


}
