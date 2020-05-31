package com.example.restaurantRecipes.ui.main.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantRecipes.R;
import com.example.restaurantRecipes.pojo.CategoryModel;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private ArrayList<CategoryModel> categoryModels =new ArrayList<>();
    private OnCategoryListener onCategoryListener;

    public CategoryAdapter(ArrayList<CategoryModel> categoryModels,OnCategoryListener onCategoryListener) {
        this.categoryModels = categoryModels;
        this.onCategoryListener=onCategoryListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false),onCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        // get current category item position
        CategoryModel currentCategoryItem=categoryModels.get(position);
        holder.categoryName.setText(currentCategoryItem.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public  class CategoryViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView categoryName;
        OnCategoryListener onCategoryListener;

        public CategoryViewHolder(@NonNull View itemView,OnCategoryListener onCategoryListener) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryTV);
            this.onCategoryListener=onCategoryListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }
    public interface OnCategoryListener{
        void onCategoryClick(int position);
    }
}
