package com.example.restaurantRecipes.pojo;

import java.util.List;

public class RecipeSearchResponse {
    private int count;
    private List<RecipesModel> recipes;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RecipesModel> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipesModel> recipes) {
        this.recipes = recipes;
    }
}

