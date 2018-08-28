package com.example.jamesburke.popularmovies.utilities;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.R;
import com.example.jamesburke.popularmovies.data.AppDatabase;

import org.w3c.dom.Text;

public class SaveFavoiteMovie {

    private static final String LOG_TAG = SaveFavoiteMovie.class.getSimpleName();




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
