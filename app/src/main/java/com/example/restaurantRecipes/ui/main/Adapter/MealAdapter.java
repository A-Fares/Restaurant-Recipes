package com.example.restaurantRecipes.ui.main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantRecipes.R;
import com.example.restaurantRecipes.pojo.RecipesModel;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private List<RecipesModel> recipesModels = new ArrayList<>();
    private Context mContext;
    private OnMealListener onMealListener;


    public void setList(List<RecipesModel> recipesModels, Context mContext,OnMealListener onMealListener) {
        this.recipesModels = recipesModels;
        this.mContext = mContext;
        this.onMealListener=onMealListener;
        notifyDataSetChanged();
    }

    public RecipesModel getSelectedRecipe(int position){
        if(recipesModels != null){
            if(recipesModels.size() > 0){
                return recipesModels.get(position);
            }
        }
        return null;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item, parent, false),onMealListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        // get current meal item position
        RecipesModel currentMeal = recipesModels.get(position);

        holder.mealTitle.setText(currentMeal.getTitle());
        Glide.with(mContext).load(currentMeal.getImage_url()).into(holder.mealImage);
        holder.publisherName.setText(currentMeal.getPublisher());
        float rating = (float) (Math.round(currentMeal.getSocial_rank())) / 20;
        holder.mealRating.setRating(rating);
    }

    @Override
    public int getItemCount() {
        return recipesModels.size();

    }

    public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mealImage;
        TextView mealTitle, publisherName;
        RatingBar mealRating;
        OnMealListener onMealListener;

        public MealViewHolder(@NonNull View itemView,OnMealListener onMealListener) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_image);
            mealTitle = itemView.findViewById(R.id.meal_titleTV);
            publisherName = itemView.findViewById(R.id.publisher_nameTV);
            mealRating = itemView.findViewById(R.id.meal_rating);

            this.onMealListener=onMealListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMealListener.onMealClick(getAdapterPosition());
        }
    }
    public interface OnMealListener{
        void onMealClick(int position);
    }
}
