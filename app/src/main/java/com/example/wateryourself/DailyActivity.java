package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class DailyActivity extends AppCompatActivity {
    private static final String FROM_DAILY = "call from daily";
    private int currentImage = 0;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DailyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
//        setActivityImage();

//        setWelcomeByNameInTop();
    }

//    private void setWelcomeByNameInTop() {
//        TextView textView = findViewById(R.id.tv_name_label);
//        textView.setText(String.format("Welcome %s !!", CupsActivity.mName));
//    }

//    private void setActivityImage() {
////        switch (CupsActivity.currentWaterAmount) { //todo change to image
////            default:
////                currentImage = 0;
////        }
//    }

    public void launchCupsActivityAgain(View view) {
        Intent intent = new Intent(this, CupsActivity.class);
        intent.putExtra(FROM_DAILY,"Daily");
        startActivity(intent);
    }



    private void setCurrentWaterAmount() {
        try {
            InputStream is = openFileInput("drinkingStatus.json");
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject reader = new JSONObject(sb.toString());
        } catch (JSONException e) { //todo change exceptions
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
