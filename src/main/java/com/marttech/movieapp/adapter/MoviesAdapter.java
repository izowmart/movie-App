package com.marttech.movieapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.marttech.movieapp.DetailActivity;
import com.marttech.movieapp.R;
import com.marttech.movieapp.model.Movie;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private Context mContext;
    private List<Movie> movieList;

    public MoviesAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext())
               .inflate(R.layout.movie_card,viewGroup,false);
     return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesAdapter.MyViewHolder holder, int i) {
        holder.title.setText(movieList.get(i).getOriginalTitle());
        String vote = Double.toString(movieList.get(i).getVoteAverage());
        holder.userrating.setText(vote);


        Glide.with(mContext)
                .load(movieList.get(i).getPosterPath())
//                .placeholder(R.drawable.load)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,userrating;
        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            userrating = itemView.findViewById(R.id.userrating);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if (pos!= RecyclerView.NO_POSITION){
                        Movie clickedDataItem = movieList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("original_title",movieList.get(pos).getOriginalTitle());
                        intent.putExtra("poster_path",movieList.get(pos).getPosterPath());
                        intent.putExtra("overview",movieList.get(pos).getOverview());
                        intent.putExtra("vote_average",Double.toString(movieList.get(pos).getVoteAverage()));
                        intent.putExtra("release_date",movieList.get(pos).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked "+ clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
