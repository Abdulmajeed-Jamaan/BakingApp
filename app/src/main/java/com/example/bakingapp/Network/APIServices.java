package com.example.bakingapp.Network;

import com.example.bakingapp.Models.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServices {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Meal>> loadMeals();

}
