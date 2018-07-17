package com.example.jamesburke.popularmovies.utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private static String CLASS_NAME = JsonUtils.class.getSimpleName();

    private final static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private final static String IMAGE_SIZE_POSTER = "w500";
    private final static String IMAGE_SIZE_BACKDROP = "w1280";
    private static String file_path_poster;
    private static String file_path_backdrop;

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
                    file_path_backdrop = IMAGE_BASE_URL + IMAGE_SIZE_BACKDROP + currentMovie.getString("backdrop_path");
                } else {
                    file_path_backdrop = "null";
                }

                if (currentMovie.getString("poster_path") != "null") {
                    file_path_poster = IMAGE_BASE_URL + IMAGE_SIZE_POSTER + currentMovie.getString("poster_path");
                } else {
                    file_path_poster = "null";
                }

                MovieData myMovieData = new MovieData(file_path_poster, jsonMovieId, jsonMovieTitle, jsonMovieDate, jsonMovieAverage, jsonMovieOverview, file_path_backdrop);
                movieList.add(myMovieData);


            }
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Problem parsing the JSON results", e);
        }

        return movieList;
    }


}




