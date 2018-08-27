package com.example.jamesburke.popularmovies.utilities;

import android.app.Activity;
import android.widget.ImageView;

import com.example.jamesburke.popularmovies.R;

public class SaveFavoiteMovie {

    ImageView starIcon;
    public void selectFavorite(Activity activity){

        starIcon = (ImageView) activity.findViewById(R.id.favoritebutton);
        starIcon.setImageResource(R.drawable.baseline_favorite_black_24);
    }

    public void deselectFavorite(Activity activity){
        starIcon = (ImageView) activity.findViewById(R.id.favoritebutton);
        starIcon.setImageResource(R.drawable.baseline_favorite_border_black_24);
    }
}
