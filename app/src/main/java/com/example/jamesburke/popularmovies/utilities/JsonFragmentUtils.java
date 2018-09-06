package com.example.jamesburke.popularmovies.utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonFragmentUtils {
    private static String CLASS_NAME = JsonFragmentUtils.class.getSimpleName();

    public static ArrayList<MovieReviewsData> parseMovieReviewsData(String json) {
        ArrayList<MovieReviewsData> movieReviewsList = new ArrayList<MovieReviewsData>();

        try {

            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray resultsArray = baseJsonResponse.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject currentMovieReview = resultsArray.getJSONObject(i);
                String jsonAuthor = currentMovieReview.getString("author");
                String jsonContent = currentMovieReview.getString("content");

                MovieReviewsData myMovieReviewsData = new MovieReviewsData(jsonAuthor, jsonContent);
                movieReviewsList.add(myMovieReviewsData);
            }


        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Problem Parsing MovieReviewsData");
        }

        return movieReviewsList;
    }

    public static ArrayList<MovieVideosData> parseMovieVideosData(String json) {
        ArrayList<MovieVideosData> movieVideosList = new ArrayList<MovieVideosData>();

        try {

            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray resultsArray = baseJsonResponse.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject currentMovieReview = resultsArray.getJSONObject(i);
                String jsonKey = currentMovieReview.getString("key");
                String jsonTrailorName= currentMovieReview.getString("name");
                String jsonTrailorSite = currentMovieReview.getString("site");

                MovieVideosData myMovieVideosData = new MovieVideosData(jsonKey, jsonTrailorName, jsonTrailorSite);
                movieVideosList.add(myMovieVideosData);
            }


        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Problem Parsing MovieVideosData");
        }

        return movieVideosList;

    }
}
