package com.example.jagrutdesai.tmdb.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jagrut.desai on 6/29/17.
 */

public class RetrofitClient {
    public static final String BASE_URL = "https://api.themoviedb.org/3/search/";
    public static final String API_KEY = "2eedcde4b435db191323e65800a55192";
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
