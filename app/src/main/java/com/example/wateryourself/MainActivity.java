package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    public static final String FROM_MAIN = "called from main"; //todo from

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences reader = getApplicationContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = reader.edit();
        editor.putBoolean("is_first", true);
        editor.apply();
        //Time to launch the another activity
        int TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = null;
                boolean isFirstRun = MyPreferences.isFirst(MainActivity.this);
                if (isFirstRun) {
                    //show start activity
                    i = new Intent(MainActivity.this, LoginActivity.class);
                } else {
                    i = new Intent(MainActivity.this, CupsActivity.class);
                    i.putExtra(MainActivity.FROM_MAIN, "MAIN");
                }
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
