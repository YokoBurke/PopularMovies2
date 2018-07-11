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

    final static String MOVIEDB_BASE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=";
    final static String API_URL = "";

    final static String PARAM_SORT = "sort_by";
    final static String PARAM_COUNTRY = "certification_country";

    public static URL buildURL(String sortOrder, String myCountry) {

        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL + API_URL).buildUpon().appendQueryParameter(PARAM_SORT, sortOrder)
                .appendQueryParameter(PARAM_COUNTRY, myCountry)
                //.appendQueryParameter(PARAM_PAGE, pageNumber)
                .build();

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
