package com.example.bakingapp.Activities;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakingapp.Fragments.DetailFragment;
import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;

import java.io.Serializable;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String STEP_ITEM = "step-item3";
    public static final String STEPS_LIST = "steps-list3";


    private List<Step> mSteps;
    private int stepIndex =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            mSteps = getIntent().getParcelableArrayListExtra(MainActivity.MEAL_ITEM);
            stepIndex = getIntent().getIntExtra(StepsActivity.STEP_ITEM,0);

            FragmentManager fragmentManager = getSupportFragmentManager();

            DetailFragment mDetailFragment = new DetailFragment();
            mDetailFragment.setmSteps(mSteps);
            mDetailFragment.setStepIndex(stepIndex);

            fragmentManager.beginTransaction()
                    .add(R.id.detail_layout, mDetailFragment)
                    .commit();
        }else{
            mSteps = savedInstanceState.getParcelableArrayList(STEPS_LIST);
            stepIndex = savedInstanceState.getInt(STEP_ITEM,0);
        }


    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putSerializable(STEPS_LIST, (Serializable) mSteps);
        outState.putInt(STEP_ITEM, stepIndex);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
