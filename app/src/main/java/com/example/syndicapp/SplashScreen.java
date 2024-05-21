package com.example.syndicapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    Animation anim;
    ImageView logo;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        anim=AnimationUtils.loadAnimation(SplashScreen.this,R.anim.progressbar_anim);
        logo=findViewById(R.id.imageView);
        bar=findViewById(R.id.progressBar);

        logo.startAnimation(anim);

        new android.os.Handler().postDelayed(
                () -> {
                    Intent intent = new Intent(SplashScreen.this, login_page.class);
                    startActivity(intent);
                    finish();
                },
                3000 //(ici, 3 secondes)
        );

    }
}