package com.example.bakingapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bakingapp.Adapters.MealsAndStepsAdapter;
import com.example.bakingapp.Models.Meal;
import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class StepsActivity extends AppCompatActivity implements MealsAndStepsAdapter.ItemClickListener {

    private final String TAG = this.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Step> steps = getIntent().getParcelableArrayListExtra(MainActivity.MEAL_ITEM);
        Log.v(TAG,steps.size()+"");

        setContentView(R.layout.activity_steps);




    }

    @Override
    public void onItemClick(int itemIndex) {
        Toast.makeText(this,itemIndex+"",Toast.LENGTH_SHORT).show();
    }
}
