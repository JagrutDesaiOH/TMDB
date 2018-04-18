package com.example.jagrutdesai.tmdb.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jagrutdesai.tmdb.Models.Movie;
import com.example.jagrutdesai.tmdb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jagrut.desai on 6/29/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    List<Movie> movieList;
    String movieImageBaseUrl = "https://image.tmdb.org/t/p/w500";


    public MovieAdapter(List<Movie> movieList){
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Picasso.with(holder.movieImage.getContext())
                .load(movieImageBaseUrl+movie.getPosterPath())
                .into(holder.movieImage);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieRating.setText(movie.getVoteAverage()+"");
        holder.movieOverview.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage ;
        TextView movieTitle;
        TextView movieRating;
        TextView movieOverview;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_poster);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            movieRating = (TextView) itemView.findViewById(R.id.movie_rating);
            movieOverview = (TextView) itemView.findViewById(R.id.movie_overview);
        }
    }

    public void setMovieList(List<Movie> movies){
        movieList = movies;
    }

    public List<Movie> getMovieList(){
        return movieList;
    }
}
