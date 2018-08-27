package com.example.jamesburke.popularmovies.utilities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity (tableName = "movie")
public class MovieData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    String myPosterUrl;
    int myMovieId;
    String myTitle;
    String myReleaseDate;
    Double myVoteAverage;
    String myOverview;
    String myBDUrl;
    Boolean myFavorites;

    public MovieData(String posterUrl, int movieID, String movieTitle, String movieReleaseDate,
                     Double movieVoteAverage, String movieOverview, String backdropUrl) {

        myPosterUrl = posterUrl;
        myMovieId = movieID;
        myTitle = movieTitle;
        myReleaseDate = movieReleaseDate;
        myVoteAverage = movieVoteAverage;
        myOverview = movieOverview;
        myBDUrl = backdropUrl;
    }

    private MovieData(Parcel inParcel) {
        myPosterUrl = inParcel.readString();
        myMovieId = inParcel.readInt();
        myTitle = inParcel.readString();
        myReleaseDate = inParcel.readString();
        myVoteAverage = inParcel.readDouble();
        myOverview = inParcel.readString();
        myBDUrl = inParcel.readString();
    }

    public String getMyUrl() {
        return myPosterUrl;
    }
    public int getMyMovieId() {
        return myMovieId;
    }
    public String getMyTitle() {
        return myTitle;
    }
    public String getMyReleaseDate() { return myReleaseDate; }

    public Double getMyVoteAverage() {
        return myVoteAverage;
    }

    public String getMyOverview() {
        return myOverview;
    }

    public String getMyBDUrl() {
        return myBDUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(myPosterUrl);
        parcel.writeInt(myMovieId);
        parcel.writeString(myTitle);
        parcel.writeString(myReleaseDate);
        parcel.writeDouble(myVoteAverage);
        parcel.writeString(myOverview);
        parcel.writeString(myBDUrl);
    }

    public static final Parcelable.Creator<MovieData> CREATOR
            = new Parcelable.Creator<MovieData>() {
        public MovieData createFromParcel(Parcel in) {
            return new MovieData(in);
        }

        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };
}
