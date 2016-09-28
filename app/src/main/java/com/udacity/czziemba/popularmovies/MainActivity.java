package com.udacity.czziemba.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.udacity.czziemba.popularmovies.adapter.MovieAdapter;
import com.udacity.czziemba.popularmovies.model.MovieListing;
import com.udacity.czziemba.popularmovies.model.MovieListingResponse;
import com.udacity.czziemba.popularmovies.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.movies_rv) RecyclerView moviesRv;

    MovieService movieService;
    List<MovieListing> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        movieService = MovieService.Factory.getInstance();

        final MovieAdapter movieAdapter = new MovieAdapter(movies);
        moviesRv.setAdapter(movieAdapter);
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));


        Call<MovieListingResponse> mostPopularMovies = movieService.getPopularMovies(1);
        mostPopularMovies.enqueue(new Callback<MovieListingResponse>() {
            @Override
            public void onResponse(Call<MovieListingResponse> call, Response<MovieListingResponse> response) {
                List<MovieListing> results = response.body().getResults();
                Timber.i("Response received with %s movies.", results.size());
                int currentSize = movieAdapter.getItemCount();
                movies.addAll(results);
                movieAdapter.notifyItemRangeInserted(currentSize, results.size());
            }

            @Override
            public void onFailure(Call<MovieListingResponse> call, Throwable t) {
                Timber.e("Failure calling %s", call.request().url());
            }
        });
    }
}
