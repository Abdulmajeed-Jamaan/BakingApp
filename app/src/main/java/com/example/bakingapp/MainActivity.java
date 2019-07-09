package com.example.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bakingapp.Models.Meal;
import com.example.bakingapp.Network.Requests.RequestMeal;
import com.example.bakingapp.Network.Response.Response_Meals;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Response_Meals {

    ProgressBar mProgressBar;
    TextView outPut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progress_bar);
        outPut = findViewById(R.id.text);

        RequestMeal meal = new RequestMeal();
        meal.response_meals = this;
        meal.start();


    }

    @Override
    public void responseMeals(boolean isSuccess, List<Meal> meals) {
        if(isSuccess){
            mProgressBar.setVisibility(View.GONE);
            for (int i = 0; i < meals.size(); i++) {
                outPut.append(meals.get(i).toString()+"\n");

            }

        }else{
            outPut.setText("no connection !!");
        }
    }
}
