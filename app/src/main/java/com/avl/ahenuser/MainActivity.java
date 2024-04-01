package com.avl.ahenuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SplashScreen;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    Animation topAnim, bottomAnim;
    ImageView drivingSchool;
    TextView ahen, solgan;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            ((Window) window).getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        // Check if user is logged in (has a phone number saved in SharedPreferences)
        boolean isLoggedIn = sharedPreferences.contains("phoneNumber");

        if (isLoggedIn) {
            // User is logged in, directly go to dashboard
            Intent intent = new Intent(MainActivity.this, dashboard.class);
            startActivity(intent);
            finish();
        } else {
            // User is not logged in, show splash screen and login activity
            // ... (rest of your existing code for splash screen and login intent)
        }

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        drivingSchool = findViewById(R.id.imageView);
        ahen = findViewById(R.id.ahen);
        solgan = findViewById(R.id.solgan);

        drivingSchool.setAnimation(topAnim);
        ahen.setAnimation(bottomAnim);
        solgan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}