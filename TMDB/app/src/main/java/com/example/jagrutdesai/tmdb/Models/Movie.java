package com.example.jagrutdesai.tmdb.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jagrut.desai on 6/29/17.
 */

public class Movie {

    @SerializedName("vote_count")
    int voteCount;
    @SerializedName("id")
    int id;
    @SerializedName("video")
    boolean hasVideo;
    @SerializedName("vote_average")
    float voteAverage;
    @SerializedName("title")
    String title;
    @SerializedName("popularity")
    float popularity;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("original_language")
    String originalLanguage;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("genre_ids")
    List<Integer> genreIds;
    @SerializedName("backdrop_path")
    String backdropPath;
    @SerializedName("adult")
    String isAdult;
    @SerializedName("overview")
    String overview;
    @SerializedName("release_date")
    String releaseDate;

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(String isAdult) {
        this.isAdult = isAdult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
