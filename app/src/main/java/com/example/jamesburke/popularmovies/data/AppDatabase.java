package com.example.jamesburke.popularmovies.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.example.jamesburke.popularmovies.utilities.MovieData;

@Database(entities = {MovieData.class}, version  = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK =  new Object();
    private static final String DATABASE_NAME = "favorite_movie.db";
    private static AppDatabase sInstance;

    public static AppDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating New Database Instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME).allowMainThreadQueries().build();
            }
        }

        if (sInstance == null) {
            Log.d(LOG_TAG, "Oh, no.  sInstance is still null!!");
        }
        Log.d(LOG_TAG, "Getting the Database Instance");
        return sInstance;
    }

    public abstract MovieDao movieDao();
}
