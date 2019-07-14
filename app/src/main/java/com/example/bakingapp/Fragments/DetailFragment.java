package com.example.bakingapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bakingapp.Activities.MainActivity;
import com.example.bakingapp.Activities.StepsActivity;
import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

    public static final String STEP_ITEM = "step-item2";
    public static final String TABLET = "tablet";
    public static final String STEPS_LIST = "steps-list2";
    private final String TAG = this.toString();

    private List<Step> mSteps;
    private int stepIndex =0;
    private SimpleExoPlayerView mVideoView;
    private SimpleExoPlayer mSimpleExoPlayer;
    private TextView mTextView;
    private Button after,before;
    private ImageView error;
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
        error = mView.findViewById(R.id.error);

        if (savedInstanceState != null){
            mSteps = (List<Step>) savedInstanceState.getSerializable(STEPS_LIST);
            stepIndex = savedInstanceState.getInt(STEP_ITEM);
            tablet = savedInstanceState.getBoolean(TABLET);
        }

        showthumpnail();


        mVideoView.setPlayer(mSimpleExoPlayer);


        mTextView.setText(mSteps.get(stepIndex).getDescription());
        getActivity().setTitle("Step : "+stepIndex);

        if (!tablet) {

            after.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (stepIndex == mSteps.size()-1) {
                        Toast.makeText(getContext(), "This is the last step", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    stepIndex++;
                    show();
                }
            });

            before.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (stepIndex == 0) {
                        return;
                    }
                    stepIndex--;
                    show();

                }
            });
        }else{
            after.setVisibility(View.GONE);
            before.setVisibility(View.GONE);
        }

        return mView;
    }

    private void initializePlayer(Uri mediaUri) {
        if (mSimpleExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mVideoView.setPlayer(mSimpleExoPlayer);



            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), "BakingApp");
// Create a data source factory.
            DataSource.Factory dataSourceFactory =
                    new DefaultHttpDataSourceFactory(userAgent);
/*        MediaSource mediaSource = new HlsMediaSource(Uri.parse("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"),
                mediaDataSourceFactory, mainHandler, null);*/

            DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                    dataSourceFactory, extractorsFactory, null, null);


            mSimpleExoPlayer.prepare(mediaSource);
            mSimpleExoPlayer.setPlayWhenReady(true);

        }


    }

    public void show(){
        mTextView.setText(mSteps.get(stepIndex).getDescription());
        getActivity().setTitle("Step : " + stepIndex);
        releasePlayer();
        showthumpnail();
    }

    public void showthumpnail(){
        Log.v(TAG,mSteps.get(stepIndex).getThumbnailURL()+"-------------------------");
        error.setVisibility(View.GONE);

        if (!mSteps.get(stepIndex).getThumbnailURL().isEmpty()) {
            initializePlayer(Uri.parse(mSteps.get(stepIndex).getThumbnailURL()));

        }else if (!mSteps.get(stepIndex).getVideoURL().isEmpty()){

            initializePlayer(Uri.parse(mSteps.get(stepIndex).getVideoURL()));


        }else{
            error.setVisibility(View.VISIBLE);
        }
    }

    private void releasePlayer() {
        if (mSimpleExoPlayer != null) {
            mSimpleExoPlayer.stop();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer = null;
        }



    }

    @Override
    public void onDestroyView() {
        releasePlayer();
        super.onDestroyView();
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

    @Override
    public void onResume() {
        super.onResume();
        mSimpleExoPlayer.setPlayWhenReady(true);

    }

    @Override
    public void onPause() {
        super.onPause();

            mSimpleExoPlayer.setPlayWhenReady(false);
    }

}
