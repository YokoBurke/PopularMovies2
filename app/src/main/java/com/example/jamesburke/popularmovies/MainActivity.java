package com.example.jamesburke.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jamesburke.popularmovies.utilities.JsonUtils;
import com.example.jamesburke.popularmovies.utilities.MovieAdapter;
import com.example.jamesburke.popularmovies.utilities.MovieData;
import com.example.jamesburke.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String mySort = "original_title.asc";

    public ArrayList<MovieData> myMovieData;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        movieTask task = new movieTask();
        task.execute();


    }



    private class movieTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = NetworkUtils.buildURL(mySort);
            Log.i("Information", searchURL.toString());
            String myString = "";
            try {
                myString = NetworkUtils.getResponseFromHttpUrl(searchURL);

            } catch (IOException e){
                Log.e("Main Activity", "Problem making the HTTP request.", e);
            }

            return  myString;
        }


        @Override
        protected void onPostExecute(String myString) {
            if (myString == null) {
                return;
            } else {

                myMovieData = JsonUtils.parseMovieData(myString);
                Toast.makeText(getApplicationContext(), "Success!!", Toast.LENGTH_LONG).show();
            }
            mAdapter = new MovieAdapter(myMovieData);
            mRecyclerView.setAdapter(mAdapter);

            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        movieTask changeTask = new movieTask();
        switch (item.getItemId()) {
            case R.id.mytitle:
                mySort = "original_title.asc";
                changeTask.execute();
                return true;
            case R.id.popularity:
                mySort = "popularity.desc";
                changeTask.execute();
                return true;
            case R.id.release_date:
                mySort = "release_date.asc";
                changeTask.execute();
                return true;
            case R.id.revenue:
                mySort = "revenue.desc";
                changeTask.execute();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}
