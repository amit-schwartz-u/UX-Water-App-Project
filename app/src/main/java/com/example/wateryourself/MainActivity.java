package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    private static int TIME_OUT = 3000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);//todo add if
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}
