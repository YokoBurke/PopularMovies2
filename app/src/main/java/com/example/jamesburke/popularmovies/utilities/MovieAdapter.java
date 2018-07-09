package com.example.jamesburke.popularmovies.utilities;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.MainActivity;
import com.example.jamesburke.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private List<MovieData> myMovieData;
    private Context myContext;

    public MovieAdapter(Context mContext, List<MovieData> theMovieData){
        myMovieData = theMovieData;
        myContext = mContext;

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView myTextView;
        public ImageView myImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.info_text);
            myImageView = (ImageView) itemView.findViewById(R.id.info_image);
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
        Picasso.with(myContext).load(myMovieData.get(position).getMyUrl()).into(holder.myImageView);
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
