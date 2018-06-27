package com.example.jamesburke.popularmovies.utilities;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter {

    private static final String CLASS_NAME = MovieAdapter.class.getSimpleName();

    public MovieAdapter(Activity context, ArrayList<MovieData> movieData){
        super(context, 0, movieData);
    }

    //https://github.com/udacity/ud839_CustomAdapter_Example/blob/master/app/src/main/java/com/example/android/flavor/AndroidFlavorAdapter.java

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View myGridView = convertView;
        if(myGridView == null) {

        }

    }



}
