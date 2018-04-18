package com.example.jagrutdesai.tmdb.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jagrutdesai.tmdb.Adapter.MovieAdapter;
import com.example.jagrutdesai.tmdb.Models.Movie;
import com.example.jagrutdesai.tmdb.Models.MovieList;
import com.example.jagrutdesai.tmdb.Network.RetrofitClient;
import com.example.jagrutdesai.tmdb.Network.interfaces.MovieListApiInterface;
import com.example.jagrutdesai.tmdb.R;

import java.nio.channels.CancelledKeyException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private RecyclerView movieListView;
    private MovieAdapter movieAdapter;
    List<Movie> movieList;
    private RecyclerView.LayoutManager mLayoutManager;
    public String defaultSearchTerm = "Game";
    private int currentPageNumber = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListView = (RecyclerView) findViewById(R.id.movie_list);
        movieListView.setHasFixedSize(true);
        movieList = new ArrayList<Movie>();
        movieAdapter = new MovieAdapter(movieList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        movieListView.setLayoutManager(mLayoutManager);
        movieListView.setAdapter(movieAdapter);

        final MovieListApiInterface movieListApiInterface =
                RetrofitClient.getRetrofit().create(MovieListApiInterface.class);
        search(movieListApiInterface, defaultSearchTerm,0);

        final EditText searchEditText = (EditText) findViewById(R.id.search_edittext);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currentPageNumber = 1;
                if(charSequence.toString().length() == 0){
                    search(movieListApiInterface,defaultSearchTerm,0);
                } else {
                    search(movieListApiInterface,charSequence.toString(),0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (event != null&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(searchEditText.getApplicationWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    return true;
                }
                return false;
            }
        });

        movieListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int last = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if(last > movieList.size() - 15) {
                    currentPageNumber++;
                    search(movieListApiInterface, searchEditText.getText().toString(), currentPageNumber);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void search (final MovieListApiInterface movieListApiInterface, String searchTerm, final int pageNumber){
        Call<MovieList> movieListCall;
        if(pageNumber == 0){
            movieListCall = movieListApiInterface.getMoviesByQuery(RetrofitClient.API_KEY,searchTerm);
        } else {
            movieListCall = movieListApiInterface.getMoviesByQuery(RetrofitClient.API_KEY,searchTerm, pageNumber);
        }
        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response.isSuccessful()){
                    if(response.body().getPage()!= 1) {
                        movieList.addAll(response.body().getMovieList());
                    } else {
                        movieList = response.body().getMovieList();
                    }
                    movieAdapter.setMovieList(movieList);
                    movieAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.e("MainActivity: ","Call<MovieList>:onFailure:Network Error");
            }
        });
    }
}
