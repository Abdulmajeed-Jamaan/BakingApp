package com.example.bakingapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakingapp.Activities.MainActivity;
import com.example.bakingapp.Adapters.MealsAndStepsAdapter;
import com.example.bakingapp.Models.Meal;
import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class StepsFragments extends Fragment{

    private final String TAG = this.getTag();
    public static final String STEPS_ARRAYLIST_TAG = "step-arraylist-tag";
    MealsAndStepsAdapter mAdapter;

    private List<Step> mSteps;
    public StepsFragments() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_steps,container,false);

        RecyclerView mRecyclerView = mView.findViewById(R.id.step_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new MealsAndStepsAdapter(getContext());
        mAdapter.setClickListener((MealsAndStepsAdapter.ItemClickListener) getContext());

        if (savedInstanceState == null) {
            mSteps = getActivity().getIntent().getParcelableArrayListExtra(MainActivity.MEAL_ITEM);
            mAdapter.setmSteps(mSteps);
        }else{
            mSteps = savedInstanceState.getParcelableArrayList(STEPS_ARRAYLIST_TAG);
            mAdapter.setmSteps(mSteps);
        }

        mRecyclerView.setAdapter(mAdapter);



        return mView;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(STEPS_ARRAYLIST_TAG, (ArrayList<Step>) mSteps);
        super.onSaveInstanceState(outState);
    }
}
