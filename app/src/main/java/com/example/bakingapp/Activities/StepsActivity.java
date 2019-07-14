package com.example.bakingapp.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakingapp.Adapters.MealsAndStepsAdapter;
import com.example.bakingapp.Fragments.DetailFragment;
import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StepsActivity extends AppCompatActivity implements MealsAndStepsAdapter.ItemClickListener {

    private final String TAG = this.toString();
    public static final String STEP_ITEM = "step-itemm";
    public static final String STEPS_LIST = "steps-listt";
    public static final String TABLET = "tablett";

    private List<Step> mSteps;
    private int stepIndex =0;

    private boolean tablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        if (savedInstanceState == null) {
            mSteps = getIntent().getParcelableArrayListExtra(MainActivity.MEAL_ITEM);
            stepIndex = getIntent().getIntExtra(StepsActivity.STEP_ITEM,0);

            if (findViewById(R.id.frame_layout_detail) != null) {
                tablet = true;

                FragmentManager fragmentManager = getSupportFragmentManager();

                DetailFragment mDetailFragment = new DetailFragment();
                mDetailFragment.setmSteps(mSteps);
                mDetailFragment.setStepIndex(stepIndex);
                mDetailFragment.setTablet(true);

                fragmentManager.beginTransaction()
                        .add(R.id.frame_layout_detail, mDetailFragment)
                        .commit();


            }

        }else{

           mSteps = (List<Step>) savedInstanceState.getSerializable(STEPS_LIST);
            stepIndex = savedInstanceState.getInt(STEP_ITEM,0);
            tablet = savedInstanceState.getBoolean(TABLET);

        }







    }

    @Override
    public void onItemClick(int itemIndex) {
        if (tablet) {

            stepIndex = itemIndex;
            DetailFragment mDetailFragment = new DetailFragment();
            mDetailFragment.setmSteps(mSteps);
            mDetailFragment.setStepIndex(stepIndex);
            mDetailFragment.setTablet(true);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout_detail, mDetailFragment)
                    .commit();

        }else {
            Intent mIntent = new Intent(this, DetailActivity.class);
            mIntent.putParcelableArrayListExtra(MainActivity.MEAL_ITEM, (ArrayList<Step>) mSteps);
            mIntent.putExtra(STEP_ITEM, itemIndex);
            startActivity(mIntent);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(STEPS_LIST, (Serializable) mSteps);
        outState.putInt(STEP_ITEM, stepIndex);
        outState.putBoolean(TABLET,tablet);
        super.onSaveInstanceState(outState);
    }



}

