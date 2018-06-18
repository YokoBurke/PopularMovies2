package com.example.jamesburke.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.jamesburke.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL myUrl = NetworkUtils.buildURL("polularity.asc");

        String myString = "";
        try {
            myString = NetworkUtils.getResponseFromHttpUrl(myUrl);
        } catch (IOException e){
            Log.e("Main Activity", "Problem making the HTTP request.", e);
        }

        Log.i("Information", myString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
