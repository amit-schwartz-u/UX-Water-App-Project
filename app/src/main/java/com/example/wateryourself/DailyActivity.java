package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
