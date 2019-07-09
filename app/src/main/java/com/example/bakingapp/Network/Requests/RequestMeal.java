package com.example.bakingapp.Network.Requests;

import com.example.bakingapp.Models.Meal;
import com.example.bakingapp.Network.APIServices;
import com.example.bakingapp.Network.NetworkUtils;
import com.example.bakingapp.Network.Response.Response_Meals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestMeal extends NetworkUtils implements Callback<List<Meal>> {

    public Response_Meals response_meals ;

    public void start(){

        APIServices mealsAPI = retrofit.create(APIServices.class);

        Call<List<Meal>> call = mealsAPI.loadMeals();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
        if(response.isSuccessful()) {
            List<Meal> mealList = response.body();

            response_meals.responseMeals(true,mealList);

        } else {
            response_meals.responseMeals(true,response.body());
        }
    }

    @Override
    public void onFailure(Call<List<Meal>> call, Throwable t) {
        response_meals.responseMeals(false,null);

    }
}
