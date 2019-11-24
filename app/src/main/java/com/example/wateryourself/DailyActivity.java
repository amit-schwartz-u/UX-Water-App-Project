package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DailyActivity extends AppCompatActivity {
    private int currentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityImage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
    }

    private void setActivityImage() {
        switch (CupsActivity.currentWaterAmount) { //todo change to image
            case 0:
                currentImage = 0;
                break;
            default:
                currentImage = 0;
        }
    }
}
