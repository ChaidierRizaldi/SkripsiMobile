package com.example.androidmedsch.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.androidmedsch.R;
import com.example.androidmedsch.ui.menu.Dashboard;
import com.example.androidmedsch.utils.SharedPreferences;

import static android.content.Context.*;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView image,logo;
    SharedPreferences sp_helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        //Animmations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.logoView);

        sp_helper = new SharedPreferences(SplashScreen.this);

        image.setAnimation(bottomAnim);
        logo.setAnimation(topAnim);

        new Handler().postDelayed(() -> {
            if (!sp_helper.checkLogin()){
                Intent intent = new Intent( SplashScreen.this, LandingPage.class);

                Pair[] pairs = new Pair[2];
                pairs [0] = new Pair<View,String> (logo, "textView");
                pairs [1] = new Pair<View,String> (image, "imageView");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                startActivity(intent,options.toBundle());
                finish();
            }else {
                Intent intent = new Intent(SplashScreen.this, Dashboard.class);
                startActivity(intent);
                finish();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }

        },SPLASH_SCREEN);


    }


}