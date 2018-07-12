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
    final static String IMAGE_SIZE = "w500";
    static String file_path_poster;
    static String file_path_backdrop;

    public static ArrayList<MovieData> parseMovieData(String json) {

        ArrayList<MovieData> movieList = new ArrayList<MovieData>();

        try {

            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray resultsArray = baseJsonResponse.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject currentMovie = resultsArray.getJSONObject(i);
                int jsonMovieId = currentMovie.getInt("id");
                String jsonMovieTitle = currentMovie.getString("title");
                String jsonMovieDate = currentMovie.getString("release_date");
                Double jsonMovieAverage = currentMovie.getDouble("vote_average");
                String jsonMovieOverview = currentMovie.getString("overview");

                if (currentMovie.getString("backdrop_path") != "null") {
                    file_path_backdrop = IMAGE_BASE_URL + IMAGE_SIZE + currentMovie.getString("backdrop_path");
                } else {
                    file_path_backdrop = "null";
                }

                if (currentMovie.getString("poster_path") != "null") {
                    file_path_poster = IMAGE_BASE_URL + IMAGE_SIZE + currentMovie.getString("poster_path");
                } else {
                    file_path_poster = "null";
                }

                MovieData myMovieData = new MovieData(file_path_poster, jsonMovieId);
                movieList.add(myMovieData);

                Log.i("Here is JSON UTil", Integer.toString(jsonMovieId) + " " + file_path_poster);


            }
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Problem parsing the JSON results", e);
        }

        return movieList;
    }


}




