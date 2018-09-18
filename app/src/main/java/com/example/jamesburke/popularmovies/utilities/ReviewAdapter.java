package com.example.jamesburke.popularmovies.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamesburke.popularmovies.R;

import java.util.List;

/**
 * Created by jamesburke on 9/3/18.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyReviewViewHolder> {

    private static final String LOG_TAG = ReviewAdapter.class.getSimpleName();

    private List<MovieReviewsData> myMovieReviewsData;
    private Context myContext;

    public ReviewAdapter(Context mContext, List<MovieReviewsData> theMovieReviewsData) {
        myMovieReviewsData = theMovieReviewsData;
        myContext = mContext;
    }

    class MyReviewViewHolder extends RecyclerView.ViewHolder {

        public TextView myReviewTextView;
        public TextView myAuthorTextView;

        public MyReviewViewHolder(View itemView) {
            super(itemView);
            myReviewTextView = (TextView) itemView.findViewById(R.id.review_text);
            myAuthorTextView = (TextView) itemView.findViewById(R.id.review_reviewer_name);
        }
    }

    @NonNull
    @Override
    public ReviewAdapter.MyReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int myLayoutId = R.layout.fragment_reviews_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyReviewViewHolder myReviewViewHolder = new MyReviewViewHolder(itemView);
        return myReviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.MyReviewViewHolder holder, int position) {

        String myReview = myMovieReviewsData.get(position).getMyContent();
        String myAuthor = myMovieReviewsData.get(position).getMyAuthor();

        holder.myAuthorTextView.setText(myAuthor);
        holder.myReviewTextView.setText(myReview);



    }

    @Override
    public int getItemCount() {
        if (myMovieReviewsData == null){
            return 0;
        }
        return myMovieReviewsData.size();
    }
}
