package com.example.jamesburke.popularmovies.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jamesburke.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private List<MovieData> myMovieData;
    private Context myContext;
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    public MovieAdapter(Context mContext, List<MovieData> theMovieData, ListItemClickListener listener){
        myMovieData = theMovieData;
        myContext = mContext;
        mOnClickListener = listener;

    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView myImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            myImageView = (ImageView) itemView.findViewById(R.id.info_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
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
