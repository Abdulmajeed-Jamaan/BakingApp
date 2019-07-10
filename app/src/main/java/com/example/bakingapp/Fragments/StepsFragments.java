package com.example.bakingapp.Fragments;

import android.content.Context;
import android.os.Bundle;
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

import java.util.List;

public class StepsFragments extends Fragment{

    private final String TAG = this.getTag();
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
        mSteps = getActivity().getIntent().getParcelableArrayListExtra(MainActivity.MEAL_ITEM);
        mAdapter.setmSteps(mSteps);
        mAdapter.setClickListener((MealsAndStepsAdapter.ItemClickListener) getContext());

        mRecyclerView.setAdapter(mAdapter);



        return mView;
    }

    public void setAdapterClickListner(MealsAndStepsAdapter.ItemClickListener mContext){
        mAdapter.setClickListener(mContext);
    }


}
