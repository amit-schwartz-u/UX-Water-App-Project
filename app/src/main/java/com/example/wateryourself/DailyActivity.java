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

/**
 * this activity is responsible for presenting the daily drinking progress. The daily goal is stated
 * and so is the amount drank so far. at the right side of the screen there is a raising bar which
 * shows progress as well. The background image is the plant which keeps growing as the user drinks.
 */
public class DailyActivity extends AppCompatActivity {
    private static final String LAST_TIME_VISITED = "last time visited";
    private int curWaterAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandleDifferentDays();
        setContentView(R.layout.activity_daily);
        Intent intent = getIntent();
        int waterToAdd = intent.getIntExtra(CupsActivity.AMOUNT_OF_WATER, 0);
        ImageView rain = findViewById(R.id.im_rain);
        if (waterToAdd != 0) { //enables rain animation if user enters an amount of water
            ObjectAnimator animation = ObjectAnimator.ofFloat(rain, "translationY", 2000f);
            animation.setDuration(3000);
            animation.start();
        } else {
            rain.setVisibility(View.INVISIBLE);
        }

        setCurrentWaterAmount(intent);

        if (curWaterAmount >= 2500) { //makes congratulation text visible when goal reached
            TextView tv1 = findViewById(R.id.tv_congratulations1);
            TextView tv2 = findViewById(R.id.tv_congratulations2);
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
        }
        TextView textView = findViewById(R.id.tv_you_drank);
        textView.setText(String.format("You Drank: %s ML today !", curWaterAmount));
        setActivityBackgroundImage();

    }

    /**
     * this method is responsible for updating the background image of the activity according to the
     * growth of the plant. (the plant is growing as the user is drinking more)
     */
    private void setActivityBackgroundImage() {
        ConstraintLayout dailyConstraintLayout = findViewById(R.id.constraintLayout1);
        if (curWaterAmount < 250) {
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

    /**
     * going back to cups activity when user wants to drink more
     */
    public void launchCupsActivityAgain(View view) {
        Intent intent = new Intent(this, CupsActivity.class);
        intent.putExtra(MainActivity.FROM, "Daily");
        startActivity(intent);
    }

    /**
     * updating the cumulative water amount after user added more water.
     * the updated data is saved to preferences in order to keep the app's state.
     */
    private void setCurrentWaterAmount(Intent intent) {
        int waterToAdd = intent.getIntExtra(CupsActivity.AMOUNT_OF_WATER, 0); // amount chosen from cups activity
        final SharedPreferences reader = getApplicationContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        curWaterAmount = reader.getInt(MyPreferences.CUMULATIVE_WATER_AMOUNT, 0);
        curWaterAmount += waterToAdd;
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putInt(MyPreferences.CUMULATIVE_WATER_AMOUNT, curWaterAmount);
        preferencesEditor.apply();
    }

    /**
     * this method checks if the current date and the last date of visiting the app is different
     */
    private void HandleDifferentDays() {
        if (!(isTheSameDay(retrieveLastDay()))) {
            resetPage();
        }
        updateTodayDate();
    }

    /**
     * this method resets the data saved in app when reaching midnight
     */
    private void resetPage() {
        final SharedPreferences reader = getApplicationContext().getSharedPreferences(MyPreferences.MY_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putInt(MyPreferences.CUMULATIVE_WATER_AMOUNT, 0);
        preferencesEditor.apply();
        curWaterAmount = 0;
    }

    /**
     * updating current date to preferences
     */
    private void updateTodayDate() {
        final SharedPreferences reader = getApplicationContext().getSharedPreferences(MyPreferences.MY_PREFERENCES, Context.MODE_PRIVATE);
        Date d = new Date();
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putLong(LAST_TIME_VISITED, d.getTime());
        preferencesEditor.apply();
    }

    /**
     * extracts the last date of visit
     */
    private Date retrieveLastDay() {
        final SharedPreferences reader = getApplicationContext().getSharedPreferences(MyPreferences.MY_PREFERENCES, Context.MODE_PRIVATE);
        Date lastDay = new Date(reader.getLong(LAST_TIME_VISITED, 0));
        return lastDay;
    }

    /**
     * is current date equals the last day of visit?
     * @param lastDate
     * @return true if equals, false otherwise
     */
    private boolean isTheSameDay(Date lastDate) {
        return DateUtils.isToday(lastDate.getTime());
    }

    /**
     * moving to garden activity in order to see all grown plants
     */
    public void goToGardenActivity(View view) {
        Intent intent = new Intent(this, GardenActivity.class);
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }

    @Override
    public void onBackPressed() { // disabling the back button of android

    }
}
