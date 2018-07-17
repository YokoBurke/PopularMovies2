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
import android.view.View;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.utilities.JsonUtils;
import com.example.jamesburke.popularmovies.utilities.MovieAdapter;
import com.example.jamesburke.popularmovies.utilities.MovieData;
import com.example.jamesburke.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String selectedData = "popular";
    private String myCertCountry = "";

    private ArrayList<MovieData> myMovieData;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycle_view);
        mRecyclerView.setHasFixedSize(true);

        emptyView = (TextView) findViewById(R.id.empty_view);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        movieTask task = new movieTask();
        task.execute();
    }



    private class movieTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = NetworkUtils.buildURL(selectedData);
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
            if (myString == "") {

                mRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
                return;
            } else {

                mRecyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
                myMovieData = JsonUtils.parseMovieData(myString);

            }
            mAdapter = new MovieAdapter(MainActivity.this, myMovieData, new MovieAdapter.ListItemClickListener() {
                @Override
                public void onListItemClick(int clickedItemIndex) {
                    /* Intent intent = new Intent(MainActivity.this, ChildActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, myMovieData);
                    startActivity(intent); */
                }
            });
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
                selectedData = "top_rate";
                Log.i("Main", selectedData);
                setTitle("Top Rated");
                changeTask.execute();
                return true;
            case R.id.popularity:
                selectedData = "popular";
                Log.i("Main", selectedData);
                setTitle("Popular");
                changeTask.execute();
                return true;
            case R.id.upcoming:
                selectedData = "upcoming";
                Log.i("Main", selectedData);
                setTitle("Upcoming");
                changeTask.execute();
                return true;
            case R.id.now_playing:
                selectedData = "now_playing";
                Log.i("Main", selectedData);
                setTitle("Now Playing");
                changeTask.execute();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}
