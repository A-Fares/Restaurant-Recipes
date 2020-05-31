package com.example.restaurantRecipes.data;

import com.example.restaurantRecipes.pojo.RecipeResponse;
import com.example.restaurantRecipes.pojo.RecipeSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {

    @GET("api/search")
    public Call<RecipeSearchResponse> searchRecipe(
            @Query("q") String query,
            @Query("page")int page);

    // GET RECIPE REQUEST
    @GET("api/get")
    public Call<RecipeResponse> getRecipe(
            @Query("rId") String recipe_id);

}
