package com.udacity.czziemba.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.czziemba.popularmovies.R;
import com.udacity.czziemba.popularmovies.model.MovieListing;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private static final String POSTER_URL_FORMAT = "http://image.tmdb.org/t/p/%s%s";
    private static final String POSTER_SIZE = "w342";
    private List<MovieListing> movies;

    public MovieAdapter(List<MovieListing> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieListingView = inflater.inflate(R.layout.item_movie_listing, parent, false);

        return new ViewHolder(movieListingView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MovieListing movieListing = movies.get(position);
        Context context = holder.moviePosterIv.getContext();
        String posterUrl = String.format(POSTER_URL_FORMAT, POSTER_SIZE, movieListing.getPosterPath());
        Picasso.with(context)
                .load(posterUrl)
                .into(holder.moviePosterIv);

        holder.moviePosterIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), String.format("Title: %s", movies.get(position).getTitle()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_poster_iv) ImageView moviePosterIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
