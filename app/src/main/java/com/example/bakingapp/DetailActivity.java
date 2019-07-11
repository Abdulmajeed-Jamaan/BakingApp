package com.example.bakingapp;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.bakingapp.Activities.MainActivity;
import com.example.bakingapp.Activities.StepsActivity;
import com.example.bakingapp.Fragments.DetailFragment;
import com.example.bakingapp.Models.Step;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private List<Step> mSteps;
    private int stepIndex =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            mSteps = getIntent().getParcelableArrayListExtra(MainActivity.MEAL_ITEM);
            stepIndex = getIntent().getIntExtra(StepsActivity.STEP_ITEM,0);
        }else{
            mSteps = savedInstanceState.getParcelableArrayList(MainActivity.MEAL_ITEM);
            stepIndex = savedInstanceState.getInt(StepsActivity.STEP_ITEM,0);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();

        DetailFragment mDetailFragment = new DetailFragment();
        mDetailFragment.setmSteps(mSteps);
        mDetailFragment.setStepIndex(stepIndex);

        fragmentManager.beginTransaction()
                .add(R.id.detail_layout, mDetailFragment)
                .commit();

    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelableArrayList(MainActivity.MEAL_ITEM, (ArrayList<Step>) mSteps);
        outState.putInt(StepsActivity.STEP_ITEM, stepIndex);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
