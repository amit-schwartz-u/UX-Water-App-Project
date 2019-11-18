package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    private Boolean isFirstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Time to launch the another activity
        int TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = null;
                if (isFirstRun) {
                    //show start activity
                    i = new Intent(MainActivity.this, LoginActivity.class);
                    isFirstRun = false;
                } else {
                    i = new Intent(MainActivity.this, CupsActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
