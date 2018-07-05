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

    public MovieAdapter(List<MovieData> theMovieData){
        myMovieData = theMovieData;

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView myTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.info_text);
        }

    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int myLayoutId = R.layout.grid_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyViewHolder movieViewHolder = new MyViewHolder(itemView);
        return movieViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.myTextView.setText(myMovieData.get(position).getMyUrl());

    }


    @Override
    public int getItemCount() {
        return myMovieData.size();
    }
}
