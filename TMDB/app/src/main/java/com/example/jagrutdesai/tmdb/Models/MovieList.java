package com.example.jagrutdesai.tmdb.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jagrut.desai on 6/29/17.
 */

public class MovieList {

    @SerializedName("page")
    int page;
    @SerializedName("total_results")
    int total_results;
    @SerializedName("total_pages")
    int total_pages;
    @SerializedName("results")
    List<Movie> movieList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
