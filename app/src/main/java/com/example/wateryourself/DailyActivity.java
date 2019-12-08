package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class DailyActivity extends AppCompatActivity {
    private static final String FROM_DAILY = "call from daily"; //todo use
    private static final String CUR_WATER_AMOUNT = "CURRENT WATER AMOUNT";
    private static final String LAST_TIME_VISITED = "LAST TIME VISITED";
    private int curWaterAmount = 0;
    ConstraintLayout dailyConstraintLayout;

    // Name of shared preferences file
    private String waterYourselfFile =
            "com.example.android.waterYourselfprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandleDifferentDays();
        setContentView(R.layout.activity_daily);
        Intent intent = getIntent();
        int waterToAdd = intent.getIntExtra(CupsActivity.AMOUNT_OF_WATER, 0);
        ImageView rain = findViewById(R.id.im_rain);
        if (waterToAdd != 0){
            ObjectAnimator animation = ObjectAnimator.ofFloat(rain, "translationY", 2000f);
            animation.setDuration(3000);
            animation.start();
        }
        else {
            rain.setVisibility(View.INVISIBLE);
        }

        setCurrentWaterAmount(intent);

        if (curWaterAmount >= 2500){
            TextView tv1 = findViewById(R.id.tv_congratulations1);
            TextView tv2 = findViewById(R.id.tv_congratulations2);
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
        }
        TextView textView = findViewById(R.id.tv_you_drank);
        textView.setText(String.format("You Drank: %s ML today !", curWaterAmount));
        setActivityBackgroundImage();

    }

    private void setActivityBackgroundImage() {
        dailyConstraintLayout = findViewById(R.id.constraintLayout1); //todo make sure 250! not 200
        if (curWaterAmount < 250) { //todo try change to 1 line
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_0);
        } else if (curWaterAmount < 500) {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_250);
        } else if (curWaterAmount < 750) {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_500);
        } else if (curWaterAmount < 1000) {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_750);
        } else if (curWaterAmount < 1250) {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_1000);
        } else if (curWaterAmount < 1500) {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_1250);
        } else if (curWaterAmount < 1750) {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_1500);
        } else if (curWaterAmount < 2000) {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_1750);
        } else if (curWaterAmount < 2250) {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_2000);
        } else if (curWaterAmount < 2500) {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_2250);
        } else {
            dailyConstraintLayout.setBackgroundResource(R.drawable.daily_stage_2500);
        }
    }

    public void launchCupsActivityAgain(View view) {
        Intent intent = new Intent(this, CupsActivity.class);
        intent.putExtra(MainActivity.FROM, "Daily");
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

    private void HandleDifferentDays() {

        if (!(isTheSameDay(retrieveLastDay()))) {
            resetPage();
        }
        updateTodayDate();

    }

    private void resetPage() {
        final SharedPreferences reader = getApplicationContext().getSharedPreferences(waterYourselfFile, Context.MODE_PRIVATE);
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putInt(CUR_WATER_AMOUNT, 0);
        preferencesEditor.apply();
        curWaterAmount = 0;
    }

    private void updateTodayDate() {
        final SharedPreferences reader = getApplicationContext().getSharedPreferences(waterYourselfFile, Context.MODE_PRIVATE);
        Date d = new Date();
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putLong(LAST_TIME_VISITED, d.getTime());
        preferencesEditor.apply();
    }

    private Date retrieveLastDay() {
        final SharedPreferences reader = getApplicationContext().getSharedPreferences(waterYourselfFile, Context.MODE_PRIVATE);
        Date lastDay = new Date(reader.getLong(LAST_TIME_VISITED, 0));
        return lastDay;
    }

    private boolean isTheSameDay(Date lastDate) {
        return DateUtils.isToday(lastDate.getTime());
    }

    public void goToGardenActivity(View view) {
        Intent intent = new Intent(this, GardenActivity.class);
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }

    @Override
    public void onBackPressed() {

    }
}
