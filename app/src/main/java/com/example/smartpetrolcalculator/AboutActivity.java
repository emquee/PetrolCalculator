package com.example.smartpetrolcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutActivity extends AppCompatActivity {

    Button textGithub;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        textGithub       = findViewById(R.id.textGithub);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        textGithub.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://github.com/emquee/PetrolCalculator"));
            startActivity(intent);
        });

        bottomNavigation.setSelectedItemId(R.id.nav_about);

        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                finish();
                return true;
            }
            return item.getItemId() == R.id.nav_about;
        });
    }
}