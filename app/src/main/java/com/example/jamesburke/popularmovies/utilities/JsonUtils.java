package com.example.jamesburke.popularmovies.utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jamesburke on 6/19/18.
 */

public class JsonUtils {

    public static String CLASS_NAME = JsonUtils.class.getSimpleName();

    public static ArrayList<MovieData> parseMovieData(String json) {

        ArrayList<MovieData> movieList = new ArrayList<MovieData>();

        try {

            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray resultsArray = baseJsonResponse.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject currentMovie = resultsArray.getJSONObject(i);
                int jsonMovieId = currentMovie.getInt("id");
                String jsonImageUrl = currentMovie.getString("poster_path");

                MovieData myMovieData = new MovieData(jsonImageUrl, jsonMovieId);
                movieList.add(myMovieData);

                Log.i("Here is JSON UTil", Integer.toString(jsonMovieId) + " " + jsonImageUrl);


            }
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Problem parsing the JSON results", e);
        }
        Log.i("Movie Adap", Integer.toString(movieList.size()));
        return movieList;
    }


}




