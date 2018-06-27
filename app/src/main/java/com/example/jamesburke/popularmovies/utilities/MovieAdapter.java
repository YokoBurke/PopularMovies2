package com.example.jamesburke.popularmovies.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamesburke.popularmovies.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<MovieData> myMovieData;

    public MovieAdapter(List<MovieData> theMovieData){
        myMovieData = theMovieData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int myLayoutId = R.layout.image_list_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyViewHolder movieViewHolder = new MyViewHolder(itemView);
        return movieViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
