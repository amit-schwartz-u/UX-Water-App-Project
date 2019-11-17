package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onRadioButtonClicked(View view) {
        //todo
    }

    public void launchCupsActivity(View view) {
        Intent intent = new Intent(this, CupsActivity.class); //todo change this?
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }
}
