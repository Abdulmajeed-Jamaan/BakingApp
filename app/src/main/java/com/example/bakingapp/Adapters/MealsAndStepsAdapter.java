package com.example.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.bakingapp.Models.Meal;
import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;
import java.util.List;

public class MealsAndStepsAdapter extends RecyclerView.Adapter<MealsAndStepsAdapter.ViewHolder>{

    private List<Meal> mMeals;
    private List<Step> mSteps;
    private Context mContext;
    private ItemClickListener mClickListener;


    public MealsAndStepsAdapter(Context mContext ) {

        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_meal_step, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        if (mMeals != null) {

            viewHolder.title.setText(mMeals.get(i).getName());

        }else {
            viewHolder.title.setText(mSteps.get(i).getDescription());
            viewHolder.title.setAllCaps(false);
        }




    }

    @Override
    public int getItemCount() {
        int size;

        if (mMeals != null) {
            size = mMeals.size();
        }else {
            size = mSteps.size();
        }

        return size;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Button title ;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.text_view);
            title.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(getAdapterPosition());

        }
    }



    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(int itemIndex);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setmMeals(List<Meal> mMeals) {
        this.mMeals = mMeals;
    }

    public void setmSteps(List<Step> mSteps) {
        this.mSteps = mSteps;
    }
}
