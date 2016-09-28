package com.udacity.czziemba.popularmovies.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.udacity.czziemba.popularmovies.BuildConfig;
import com.udacity.czziemba.popularmovies.model.MovieListingResponse;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    class Factory {
        private static final String BASE_URL = "http://api.themoviedb.org/3/";
        private static final String API_KEY = BuildConfig.TMDB_API_KEY;
        private static MovieService movieService;

        public static MovieService getInstance() {
            if (movieService == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()))
                        .client(new OkHttpClient.Builder()
                                .addInterceptor(new Interceptor() {
                                    @Override
                                    public Response intercept(Chain chain) throws IOException {
                                        HttpUrl newUrl = chain.request().url().newBuilder()
                                                .addQueryParameter("api_key", API_KEY)
                                                .build();

                                        Request newRequest = chain.request().newBuilder()
                                                .url(newUrl)
                                                .build();

                                        return chain.proceed(newRequest);
                                    }
                                })
                                .build())
                        .build();

                movieService = retrofit.create(MovieService.class);
            }
            return movieService;
        }
    }

    @GET("movie/popular")
    Call<MovieListingResponse> getPopularMovies(@Query("page") int page);

    @GET("movie/top_rated")
    Call<MovieListingResponse> getTopRatedMovies(@Query("page") int page);
}
