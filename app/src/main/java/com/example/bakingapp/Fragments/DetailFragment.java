package com.example.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bakingapp.Activities.MainActivity;
import com.example.bakingapp.Activities.StepsActivity;
import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

    public static final String STEP_ITEM = "step-item2";
    public static final String TABLET = "tablet";
    public static final String STEPS_LIST = "steps-list2";

    private List<Step> mSteps;
    private int stepIndex =0;
    private VideoView mVideoView;
    private TextView mTextView;
    private Button after,before;
    private boolean tablet;

    public void setmSteps(List<Step> mSteps) {
        this.mSteps = mSteps;
    }

    public void setStepIndex(int stepIndex) {
        this.stepIndex = stepIndex;
    }

    public DetailFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_details,container,false);



        mVideoView = mView.findViewById(R.id.video_view);
        mTextView = mView.findViewById(R.id.text_view);
        before = mView.findViewById(R.id.btn_before);
        after = mView.findViewById(R.id.btn_after);

        if (savedInstanceState != null){
            mSteps = (List<Step>) savedInstanceState.getSerializable(STEPS_LIST);
            stepIndex = savedInstanceState.getInt(STEP_ITEM);
            tablet = savedInstanceState.getBoolean(TABLET);
        }

        mVideoView.setVideoPath(mSteps.get(stepIndex).getVideoURL());
        mVideoView.start();

        mTextView.setText(mSteps.get(stepIndex).getDescription());
        getActivity().setTitle("Step : "+stepIndex++);

        if (!tablet) {

            after.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stepIndex++;
                    if (stepIndex >= mSteps.size()) {
                        Toast.makeText(getContext(), "This is the last step", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    getActivity().setTitle("Step : " + stepIndex);
                    showStep();
                }
            });

            before.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (stepIndex == 0) {
                        return;
                    }
                    getActivity().setTitle("Step : " + stepIndex);
                    stepIndex--;
                    showStep();
                }
            });
        }else{
            after.setVisibility(View.GONE);
            before.setVisibility(View.GONE);
        }

        return mView;
    }

    public void showStep(){
        mVideoView.setVideoPath(mSteps.get(stepIndex).getVideoURL());
        mVideoView.start();

        mTextView.setText(mSteps.get(stepIndex).getDescription());

        getActivity().setTitle("Step : " + stepIndex);

    }


    public void setTablet(boolean tablet) {
        this.tablet = tablet;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(STEPS_LIST, (Serializable) mSteps);
        outState.putInt(STEP_ITEM, stepIndex);
        outState.putBoolean(TABLET,tablet);
        super.onSaveInstanceState(outState);
    }
}
