package com.example.restaurantRecipes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurantRecipes.R;
import com.example.restaurantRecipes.ui.main.MainActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN=2500;

    Animation topAnim, bottomAnim;
    ImageView foodLogo;
    TextView appNameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Views
        foodLogo = findViewById(R.id.food_logo);
        appNameTV = findViewById(R.id.app_name_TV);

        //set animation to view
        foodLogo.setAnimation(topAnim);
        appNameTV.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}