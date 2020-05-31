package com.example.restaurantRecipes.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class RecipesModel implements Parcelable {
    private String image_url, publisher,
            _id, recipe_id, title;
    private float social_rank;
    private String[] ingredients;

    protected RecipesModel(Parcel in) {
        image_url = in.readString();
        publisher = in.readString();
        _id = in.readString();
        recipe_id = in.readString();
        title = in.readString();
        social_rank = in.readFloat();
        ingredients = in.createStringArray();
    }

    public static final Creator<RecipesModel> CREATOR = new Creator<RecipesModel>() {
        @Override
        public RecipesModel createFromParcel(Parcel in) {
            return new RecipesModel(in);
        }

        @Override
        public RecipesModel[] newArray(int size) {
            return new RecipesModel[size];
        }
    };

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getSocial_rank() {
        return social_rank;
    }

    public void setSocial_rank(float social_rank) {
        this.social_rank = social_rank;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image_url);
        dest.writeString(publisher);
        dest.writeString(_id);
        dest.writeString(recipe_id);
        dest.writeString(title);
        dest.writeFloat(social_rank);
        dest.writeStringArray(ingredients);
    }
}
