package com.example.bakingapp.Network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

        public Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        public Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

}
