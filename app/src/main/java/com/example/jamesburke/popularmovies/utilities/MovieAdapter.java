package com.example.jamesburke.popularmovies.utilities;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private List<MovieData> myMovieData;

    //constructor
    public MovieAdapter(List<MovieData> theMovieData){
        myMovieData = theMovieData;

    }

    //View Holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView myTextView;

        //constructor
        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.info_text);
        }

    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        MyViewHolder movieViewHolder = new MyViewHolder(itemView);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int myData;
        myData = myMovieData.get(position).getMyMovieId();
        Log.i("Movie Adapter", Integer.toString(myData));
        holder.myTextView.setText(myData);


    }

    @Override
    public int getItemCount() {

        if (myMovieData == null){
            Log.i("Movie Adap", "Array is Null");
            return 0;
        }

        return myMovieData.size();
    }
}
