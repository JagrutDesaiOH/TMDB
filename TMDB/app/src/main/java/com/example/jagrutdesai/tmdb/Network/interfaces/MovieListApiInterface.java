package com.example.jagrutdesai.tmdb.Network.interfaces;

import com.example.jagrutdesai.tmdb.Models.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jagrut.desai on 6/29/17.
 */

public interface MovieListApiInterface {
    @GET("movie?")
    Call<MovieList> getMoviesByQuery(@Query("api_key") String api_key, @Query("query") String query);
    @GET("movie?")
    Call<MovieList> getMoviesByQuery(@Query("api_key") String api_key, @Query("query") String query, @Query("page") int pageNumber);

}
