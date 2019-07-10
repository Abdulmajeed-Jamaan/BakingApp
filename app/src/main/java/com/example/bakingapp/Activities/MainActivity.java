package com.example.bakingapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakingapp.Adapters.MealsAndStepsAdapter;
import com.example.bakingapp.Models.Meal;
import com.example.bakingapp.Models.Step;
import com.example.bakingapp.Network.Requests.RequestMeal;
import com.example.bakingapp.Network.Response.Response_Meals;
import com.example.bakingapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Response_Meals ,MealsAndStepsAdapter.ItemClickListener{

    public static final String MEALS_ARRAY_LIST ="meals-array-list";
    public static final String MEAL_ITEM ="meal-item";
    ProgressBar mProgressBar;
    TextView outPut;
    RecyclerView mRecyclerView;
    MealsAndStepsAdapter mAdapter;
    List<Meal> mMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progress_bar);
        outPut = findViewById(R.id.text);
        mRecyclerView = findViewById(R.id.meal_recycler);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,calculateNoOfColumns(this)));
        mAdapter = new MealsAndStepsAdapter(this);
        mAdapter.setClickListener(this);

        if(savedInstanceState ==null) {
            RequestMeal meal = new RequestMeal();
            meal.response_meals = this;
            meal.start();

        }else{
            mProgressBar.setVisibility(View.GONE);
            mMeals = savedInstanceState.getParcelableArrayList(MEALS_ARRAY_LIST) ;
            mAdapter.setmMeals(mMeals);
            mRecyclerView.setAdapter(mAdapter);
        }


    }


    @Override
    public void responseMeals(boolean isSuccess, List<Meal> meals) {
        if(isSuccess){
            mProgressBar.setVisibility(View.GONE);
            mAdapter.setmMeals(meals);
            mRecyclerView.setAdapter(mAdapter);
            mMeals = meals;


        }else{
            mProgressBar.setVisibility(View.GONE);
            outPut.setText("no connection !!");
        }
    }



    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if(noOfColumns < 2)
            noOfColumns = 1;
        return noOfColumns;
    }

    @Override
    public void onItemClick(int itemIndex) {
        Intent mIntent = new Intent(this,StepsActivity.class);

        ArrayList<Step> arrlistofOptions = new ArrayList<>(mMeals.get(itemIndex).getSteps());
        mIntent.putParcelableArrayListExtra(MEAL_ITEM,arrlistofOptions);
        startActivity(mIntent);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MEALS_ARRAY_LIST, (ArrayList<Meal>) mMeals);
        super.onSaveInstanceState(outState);
    }
}
