package com.example.jamesburke.popularmovies.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.R;
import com.example.jamesburke.popularmovies.data.AppDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private static final String LOG_TAG = FavoriteAdapter.class.getSimpleName();

    private List<MovieData> mMovieDatas;
    private Context context;

    public FavoriteAdapter (Context context){
        this.context = context;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position){
        MovieData movieData = mMovieDatas.get(position);
        String posterURL = movieData.getMyPosterUrl();
        String movieTitle = movieData.getMyTitle();


        if (posterURL.substring(posterURL.length() - 4) == "null") {
            holder.moviePosterView.setImageResource(R.drawable.baseline_image_black_48);
        } else {
            Picasso.with(context).load(posterURL).into(holder.moviePosterView);
        }

        holder.movieTitleView.setText(movieTitle);
    }

    @Override
    public int getItemCount() {
        if (mMovieDatas == null) {
            return 0;
        }
        return mMovieDatas.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePosterView;
        TextView movieTitleView;

        public FavoriteViewHolder (View itemView) {
            super(itemView);

            moviePosterView = itemView.findViewById(R.id.favorite_poster_image);
            movieTitleView = itemView.findViewById(R.id.favorite_title);
        }

    }

}
