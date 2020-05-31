package com.example.restaurantRecipes.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantRecipes.R;
import com.example.restaurantRecipes.pojo.CategoryModel;
import com.example.restaurantRecipes.pojo.RecipeSearchResponse;
import com.example.restaurantRecipes.ui.main.Adapter.CategoryAdapter;
import com.example.restaurantRecipes.ui.main.Adapter.MealAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryListener, MealAdapter.OnMealListener {
    private RecyclerView recyclerViewMeal;
    private ArrayList<CategoryModel> categoryModels;
   protected MealViewModel mealViewModel;
   private MealAdapter mealAdapter;
    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // VIEWS
        recyclerViewMeal = findViewById(R.id.recycler_view_meal);
        mSearchView=(SearchView)findViewById(R.id.search_view);
        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        initSearchView();
        initRecyclerViewMeal();
        getCategoryModel();
    }
    private void onMealObserver(){
        mealViewModel.mutableLiveData.observe(this, new Observer<RecipeSearchResponse>() {
            @Override
            public void onChanged(RecipeSearchResponse recipeSearchResponse) {
                mealAdapter.setList(recipeSearchResponse.getRecipes(), MainActivity.this, MainActivity.this);
            }
        });
    }
    private void initRecyclerViewMeal(){
        searchRecipesApi(null);
        mealAdapter  = new MealAdapter();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewMeal.setLayoutManager(layoutManager);
        recyclerViewMeal.setAdapter(mealAdapter);
        onMealObserver();
    }
    private void searchRecipesApi(String query){
        recyclerViewMeal.smoothScrollToPosition(0);
        mealViewModel.getSearchRecipe(query);
        mSearchView.clearFocus();
    }
    private void getCategoryModel() {
        categoryModels = new ArrayList<>();
        categoryModels.add(new CategoryModel("Breakfast"));
        categoryModels.add(new CategoryModel("Barbeque"));
        categoryModels.add(new CategoryModel("Brunch"));
        categoryModels.add(new CategoryModel("Chicken"));
        categoryModels.add(new CategoryModel("Beef"));
        categoryModels.add(new CategoryModel("Dinner"));
        categoryModels.add(new CategoryModel("Italian"));
        categoryModels.add(new CategoryModel("Wine"));
        // call method for initializing Horizontal recyclerview to show category of food
        initRecyclerViewCategory();
    }

    private void initRecyclerViewCategory() {
        RecyclerView recyclerViewCategory = findViewById(R.id.recycler_view_category);
        recyclerViewCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModels, this);
        recyclerViewCategory.setAdapter(categoryAdapter);
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryModels.get(position).getCategoryName();
        mealViewModel.getSearchRecipe(category);
    }

    // on click the meal item send data to recipe Activity
    @Override
    public void onMealClick(int position) {
        Intent intent=new Intent(MainActivity.this,RecipeActivity.class);
        intent.putExtra("Recipe",mealAdapter.getSelectedRecipe(position));
        startActivity(intent);
    }

    // initialize search view
    private void initSearchView(){
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchRecipesApi(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

}
