package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GardenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
    }

    /**
     * Returns to the Daily activity page when the button is clicked
     * @param view
     */
    public void backToDailyActivity(View view) {
        Intent intent = new Intent(GardenActivity.this, DailyActivity.class);
        startActivity(intent);
    }

    @Override
    /**
     * Disables the phone's back button to prevent its use in this activity of the app
     */
    public void onBackPressed() {

    }
}
