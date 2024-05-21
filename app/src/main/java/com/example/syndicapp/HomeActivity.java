package com.example.syndicapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends Activity {
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_activity);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        /*bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
// Action lorsque l'élément "Home" est sélectionné
                    return true;
                case R.id.menu_contact:
// Action lorsque l'élément "Contact" est sélectionné
                    return true;
                case R.id.menu_signout:
// Action lorsque l'élément "Sign Out" est sélectionné
                    return true;
            }
            return false;
        });*/



    }

    public void OnMapClick(View view) {
        Intent intent = new Intent(HomeActivity.this, MapActivity.class);
        startActivity(intent);
        finish();
    }
}
