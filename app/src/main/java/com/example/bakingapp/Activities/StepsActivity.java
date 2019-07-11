package com.example.bakingapp.Activities;

import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bakingapp.Adapters.MealsAndStepsAdapter;
import com.example.bakingapp.DetailActivity;
import com.example.bakingapp.Fragments.DetailFragment;
import com.example.bakingapp.Fragments.StepsFragments;
import com.example.bakingapp.Models.Meal;
import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class StepsActivity extends AppCompatActivity implements MealsAndStepsAdapter.ItemClickListener {

    private final String TAG = this.toString();
    public static final String STEP_ITEM = "step-item";

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

        }else{
           mSteps = savedInstanceState.getParcelableArrayList(MainActivity.MEAL_ITEM);
           stepIndex = savedInstanceState.getInt(StepsActivity.STEP_ITEM,0);

        }

        Log.v(TAG,mSteps.size()+"===");

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
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelableArrayList(StepsFragments.STEPS_ARRAYLIST_TAG, (ArrayList<Step>) mSteps);
        outState.putInt(StepsActivity.STEP_ITEM, stepIndex);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
