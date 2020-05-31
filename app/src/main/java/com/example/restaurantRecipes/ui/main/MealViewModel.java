package com.example.restaurantRecipes.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restaurantRecipes.data.MealApiServices;
import com.example.restaurantRecipes.pojo.RecipeResponse;
import com.example.restaurantRecipes.pojo.RecipeSearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealViewModel extends ViewModel {
    MutableLiveData<RecipeSearchResponse> mutableLiveData = new MutableLiveData<>();
    MutableLiveData<RecipeResponse> recipesModelMutableLiveData = new MutableLiveData<RecipeResponse>();

    public void getSearchRecipe(String category) {
        MealApiServices.getINSTANCE().searchRecipe(category).enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {

            }
        });
    }

    public void getRecipe(String recipe_id) {
       MealApiServices.getINSTANCE().getRecipe(recipe_id).enqueue(new Callback<RecipeResponse>() {
           @Override
           public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
               recipesModelMutableLiveData.postValue(response.body());
           }

           @Override
           public void onFailure(Call<RecipeResponse> call, Throwable t) {

           }
       });
    }
}
