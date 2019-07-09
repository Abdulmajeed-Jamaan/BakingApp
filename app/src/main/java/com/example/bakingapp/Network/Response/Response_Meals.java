package com.example.bakingapp.Network.Response;

import com.example.bakingapp.Models.Meal;

import java.util.List;

public interface Response_Meals {

    void responseMeals(boolean isSuccess , List<Meal> meals);
}
