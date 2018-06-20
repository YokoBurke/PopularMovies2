package com.example.jamesburke.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.jamesburke.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieAsyncTask task = new MovieAsyncTask();
        task.execute();

    }

    public class MovieAsyncTask extends AsyncTask<URL, Void, String> {


        URL myUrl = NetworkUtils.buildURL("polularity.asc");
        String myString = "";

        @Override
        protected String doInBackground(URL... params) {


            try {
                myString = NetworkUtils.getResponseFromHttpUrl(myUrl);
            } catch (IOException e) {
                Log.e("Main Activity", "Problem making the HTTP request.", e);
            }

            Log.i("Information", myString);
            return myString;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {

            if (myString == null) {
                return;
            }
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
