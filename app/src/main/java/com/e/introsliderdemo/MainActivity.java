package com.e.introsliderdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadSlides(View view) {

        new PrefaranceManager(this).clearPreferance();
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }
}
