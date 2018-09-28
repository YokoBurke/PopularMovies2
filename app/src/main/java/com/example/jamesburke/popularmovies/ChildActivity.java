package com.example.jamesburke.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jamesburke.popularmovies.Fragment.SimpleFragmentPagerAdapter;
import com.example.jamesburke.popularmovies.utilities.MovieData;

public class ChildActivity extends AppCompatActivity {

    private MovieData childMovieData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        android.support.v7.widget.Toolbar myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.child_toolbar);
        setSupportActionBar(myToolbar);



        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent childIntent = getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {
            childMovieData = (MovieData) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);

        }

       getSupportActionBar().setTitle(childMovieData.getMyTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.i("Child Activ", childMovieData.getMyTitle());

    }
    public MovieData getMyData() {
        //https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        return childMovieData;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.childmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.child_favorite:
                Intent intent = new Intent(ChildActivity.this, FavoriteActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

}
