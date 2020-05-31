package com.example.restaurantRecipes.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.restaurantRecipes.R;
import com.example.restaurantRecipes.pojo.RecipeResponse;
import com.example.restaurantRecipes.pojo.RecipesModel;

public class RecipeActivity extends AppCompatActivity {

    private String recipeID;
    private ImageView recipeImage;
    private TextView recipeTitle, publisherName;
    private RatingBar recipeRating;
    private LinearLayout mRecipeIngredientsContainer;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // SET VIEWS
        mRecipeIngredientsContainer = findViewById(R.id.ingredients_container);
        recipeImage = findViewById(R.id.recipe_image);
        recipeTitle = findViewById(R.id.recipe_titleTV);
        publisherName = findViewById(R.id.publisherTV);
        recipeRating = findViewById(R.id.recipe_rating);
        mProgressBar = findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.VISIBLE);

        if (getIntent().hasExtra("Recipe")) {
            RecipesModel recipesModel = getIntent().getParcelableExtra("Recipe");
            assert recipesModel != null;
            recipeID = recipesModel.getRecipe_id();
            recipeObserver();
        }

    }

    private void recipeObserver() {
        MealViewModel mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);
        mealViewModel.getRecipe(recipeID);
        mealViewModel.recipesModelMutableLiveData.observe(this, new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                setRecipeProperties(recipeResponse);
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }
        // set properties for recipe from api data retried from item clicked
    private void setRecipeProperties(RecipeResponse recipeResponse) {
        if (recipeResponse != null) {

            Glide.with(this)
                    .load(recipeResponse.getRecipe().getImage_url())
                    .into(recipeImage);
            recipeImage.setClipToOutline(true);
            recipeTitle.setText(recipeResponse.getRecipe().getTitle());
            publisherName.setText(recipeResponse.getRecipe().getPublisher());

            float rating = (float) (Math.round(recipeResponse.getRecipe().getSocial_rank())) / 20;
            recipeRating.setRating(rating);

            getIngredients(recipeResponse);

        }
    }

    private void getIngredients(RecipeResponse recipeResponse) {
        if (recipeResponse.getRecipe().getIngredients() != null) {
            for (String ingredient : recipeResponse.getRecipe().getIngredients()) {
                TextView textView = new TextView(RecipeActivity.this);
                textView.setText(ingredient);
                textView.setTextSize(17);
                textView.setTextColor(Color.parseColor("#777777"));
                textView.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                mRecipeIngredientsContainer.addView(textView);
            }
        } else {
            TextView textView = new TextView(this);
            textView.setText("Error retrieving ingredients.\nCheck network connection.");
            textView.setTextSize(17);
            textView.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            mRecipeIngredientsContainer.addView(textView);
        }
    }
}