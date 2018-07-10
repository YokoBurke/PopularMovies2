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

    final static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    final static String IMAGE_SIZE = "w185";
    static String file_path;

    public static ArrayList<MovieData> parseMovieData(String json) {

        ArrayList<MovieData> movieList = new ArrayList<MovieData>();

        try {

            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray resultsArray = baseJsonResponse.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject currentMovie = resultsArray.getJSONObject(i);
                int jsonMovieId = currentMovie.getInt("id");

                if (currentMovie.getString("poster_path") != "null") {
                    file_path = IMAGE_BASE_URL + IMAGE_SIZE + currentMovie.getString("poster_path");
                } else {
                    file_path = "null";
                }

                MovieData myMovieData = new MovieData(file_path, jsonMovieId);
                movieList.add(myMovieData);

                Log.i("Here is JSON UTil", Integer.toString(jsonMovieId) + " " + file_path);


            }
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Problem parsing the JSON results", e);
        }

        return movieList;
    }


}




