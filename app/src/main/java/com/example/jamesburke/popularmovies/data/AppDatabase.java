package com.example.jamesburke.popularmovies.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK =  new Object();
    private static final String DATABASE_NAME = "favorite_movie";
    private static AppDatabase sInstance;

    public static AppDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating New Database Instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME).allowMainThreadQueries().build();
            }
        }
        Log.d(LOG_TAG, "Getting the Database Instance");
        return sInstance;
    }

    public abstract MovieDao movieDao();
}
