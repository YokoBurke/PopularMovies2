package com.example.jamesburke.popularmovies.utilities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity (tableName = "movie")
public class MovieData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int myId;
    private String myPosterUrl;
    private int myMovieId;
    private String myTitle;
    private String myReleaseDate;
    private Double myVoteAverage;
    private String myOverview;
    private String myBDUrl;
    private boolean myFavorites;

    @Ignore
    public MovieData(String myPosterUrl, int myMovieId , String myTitle, String myReleaseDate,
                     Double myVoteAverage, String myOverview, String myBDUrl, boolean myFavorites) {

        this.myPosterUrl = myPosterUrl;
        this.myMovieId = myMovieId;
        this.myTitle = myTitle;
        this.myReleaseDate = myReleaseDate;
        this.myVoteAverage = myVoteAverage;
        this.myOverview = myOverview;
        this.myBDUrl = myBDUrl;
        this.myFavorites = myFavorites;
    }

    public MovieData(int myId, String myPosterUrl, int myMovieId, String myTitle, String myReleaseDate,
                     Double myVoteAverage, String myOverview, String myBDUrl, boolean myFavorites) {

        this.myId = myId;
        this.myPosterUrl = myPosterUrl;
        this.myMovieId = myMovieId ;
        this.myTitle = myTitle;
        this.myReleaseDate = myReleaseDate;
        this.myVoteAverage = myVoteAverage;
        this.myOverview = myOverview;
        this.myBDUrl = myBDUrl;
        this.myFavorites = myFavorites;
    }

    public MovieData(Parcel inParcel) {
        myId = inParcel.readInt();
        myPosterUrl = inParcel.readString();
        myMovieId = inParcel.readInt();
        myTitle = inParcel.readString();
        myReleaseDate = inParcel.readString();
        myVoteAverage = inParcel.readDouble();
        myOverview = inParcel.readString();
        myBDUrl = inParcel.readString();
    }

    public int getMyId() { return myId;}
    public String getMyPosterUrl() {
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
    public boolean getMyFavorites() {return myFavorites;}

    public void setMyFavorites() {
        myFavorites = true;
    }

    public void unSetMyFavorites() {
        myFavorites = false;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(myId);
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
