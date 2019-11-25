package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DailyActivity extends AppCompatActivity {
    private static final String FROM_DAILY = "call from daily"; //todo use
    private static final String CUR_WATER_AMOUNT = "CUR WATER AMOUNT";
    private int curWaterAmount = 0;
    ConstraintLayout rl;
    // Shared preferences object
    private SharedPreferences mPreferences;

    // Name of shared preferences file
    private String waterYourselfFile =
            "com.example.android.waterYourselfprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        Intent intent = getIntent();
        setCurrentWaterAmount(intent);
        setActivityBackgroundImage();
        setWelcomeByNameInTop();
    }

    private void setWelcomeByNameInTop() {
        TextView textView = findViewById(R.id.tv_hello_by_name);
        textView.setText(String.format("Hi %s !", CupsActivity.mName));
    }

    private void setActivityBackgroundImage() {
        rl = findViewById(R.id.constraintLayout1); //todo rename r1
        if (curWaterAmount <= 625) {
            rl.setBackgroundResource(R.drawable.daily1);
        } else if (curWaterAmount <= 1250) {
            rl.setBackgroundResource(R.drawable.daily2);
        } else if (curWaterAmount <= 2000) {
            rl.setBackgroundResource(R.drawable.daily3);
        } else {
            rl.setBackgroundResource(R.drawable.daily4);
        }

    }

    public void launchCupsActivityAgain(View view) {
        Intent intent = new Intent(this, CupsActivity.class);
        intent.putExtra(MainActivity.FROM_MAIN, "Daily");
        startActivity(intent);
    }


    private void setCurrentWaterAmount(Intent intent) {
        int waterToAdd = intent.getIntExtra(CupsActivity.AMOUNT_OF_WATER, 0);
        final SharedPreferences reader = getApplicationContext().getSharedPreferences(waterYourselfFile, Context.MODE_PRIVATE);
        curWaterAmount = reader.getInt(CUR_WATER_AMOUNT, 0);
        curWaterAmount += waterToAdd;
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putInt(CUR_WATER_AMOUNT, curWaterAmount);
        preferencesEditor.apply();
    }

    public void goToGardenActivity(View view) {
        Intent intent = new Intent(this, GardenActivity.class);
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }
}
