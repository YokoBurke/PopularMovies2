package com.example.jamesburke.popularmovies.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jamesburke.popularmovies.utilities.MovieData;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie ORDER BY myId")
    List<MovieData> loadAllMovie();

    @Query("SELECT COUNT(myID) FROM movie")
    int findMovie();

    @Query("SELECT COUNT(myMovieId) FROM movie WHERE myMovieId = 'findMovieID'")
    Integer checkExistance(int findMovieID);

    @Insert
    void insertMovie(MovieData movieData);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(MovieData movieData);

    @Delete
    void deleteMovie(MovieData movieData);

}
