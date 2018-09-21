package com.example.jamesburke.popularmovies.utilities;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.jamesburke.popularmovies.data.AppDatabase;

import java.util.List;

/**
 * Created by jamesburke on 9/18/18.
 */

public class MainViewModel extends AndroidViewModel {

    private static final String LOG_TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<MovieData>> movieData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getsInstance(this.getApplication());
        Log.d(LOG_TAG, "Actively Retrieving the Movie Data from the Database");
        movieData = database.movieDao().loadAllMovie();
    }

    public LiveData<List<MovieData>> getMovieData() {
        return movieData;
    }
}
