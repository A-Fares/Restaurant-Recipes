package com.example.restaurantRecipes.pojo;

public class CategoryModel {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryModel(String categoryName) {
        this.categoryName = categoryName;
    }
}
