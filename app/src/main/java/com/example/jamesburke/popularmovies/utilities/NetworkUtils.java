package com.example.jamesburke.popularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class NetworkUtils {

    final static String CLASS_NAME = String.class.getSimpleName();

    final static String MOVIE_POPULAR_BASE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=";
    final static String MOVIE_TOP_RATE_BASE_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=";
    final static String MOVIE_UPCOMING_BASE_URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=";
    final static String MOVIE_NOW_PLAYING_BASE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=";

    final static String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";
    final static String API_URL = "73e4a66f623745e5138464b4ac6fb93b";




    public static URL buildURL(String selectedData, int movieID) {

        String selectedBase = "";
        Log.i("Netowrk Utils", selectedData);
        switch (selectedData) {
            case "top_rate": selectedBase = MOVIE_TOP_RATE_BASE_URL;
                break;
            case "popular": selectedBase = MOVIE_POPULAR_BASE_URL;
                break;
            case "upcoming": selectedBase = MOVIE_UPCOMING_BASE_URL;
                break;
            case "now_playing": selectedBase = MOVIE_NOW_PLAYING_BASE_URL;
                break;
        }

        Uri builtUri = Uri.parse(selectedBase + API_URL);

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
       String jsonResponse = "";
       HttpURLConnection urlConnection = null;
       InputStream inputStream = null;

       try {
           urlConnection = (HttpURLConnection) url.openConnection();
           urlConnection.setRequestMethod("GET");
           urlConnection.setReadTimeout(10000);
           urlConnection.setConnectTimeout(15000);
           urlConnection.connect();

           if (urlConnection.getResponseCode() == 200) {

               inputStream = urlConnection.getInputStream();
               jsonResponse = readFromStream(inputStream);
           } else {
               Log.e(CLASS_NAME, "Error response code: " + urlConnection.getResponseCode());
           }

       }catch (IOException e) {
           Log.e(CLASS_NAME , "Problem retrieving the JSON result.", e);
       } finally {
           if (urlConnection != null) {
               urlConnection.disconnect();
           }
           if (inputStream != null) {

               inputStream.close();
           }
       }

       return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
