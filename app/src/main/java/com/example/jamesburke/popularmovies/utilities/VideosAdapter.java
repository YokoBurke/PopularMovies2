package com.example.jamesburke.popularmovies.utilities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jamesburke on 9/3/18.
 */

public class VideosAdapter extends RecyclerView.Adapter <VideosAdapter.MyVideosViewHolder> {

    private List<MovieVideosData> myMovieVideosData;
    private Context myContext;
    final private ListItemClickListener mOnClickListener;

    String youTubeUrl;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public VideosAdapter(Context mContext, List<MovieVideosData> theMovieVideosData, VideosAdapter.ListItemClickListener listener) {
        myMovieVideosData = theMovieVideosData;
        myContext = mContext;
        mOnClickListener = listener;

    }

    class MyVideosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView myImageView;
        public TextView myTextView;

        public MyVideosViewHolder(View itemView) {
            super(itemView);
            myImageView = (ImageView) itemView.findViewById(R.id.trailer_image);
            myTextView = (TextView) itemView.findViewById(R.id.trailor_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youTubeUrl));
            myContext.startActivity(intent);

        }
    }


    @NonNull
    @Override
    public VideosAdapter.MyVideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int myLayoutID = R.layout.fragment_videos_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutID, parent, false);
        MyVideosViewHolder myVideosViewHolder = new MyVideosViewHolder(itemView);
        return myVideosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.MyVideosViewHolder holder, int position) {

        String myTitle = myMovieVideosData.get(position).getMyTrailorName();
        String myImgUrl = myMovieVideosData.get(position).returnYoutubeImageURL();
        youTubeUrl = myMovieVideosData.get(position).returnYouTubeVideoURL();

        Picasso.with(myContext).load(myImgUrl).into(holder.myImageView);
        holder.myTextView.setText(myTitle);

    }

    @Override
    public int getItemCount() {

        if (myMovieVideosData == null) {
            return 0;
        }

        return myMovieVideosData.size();
    }

}
