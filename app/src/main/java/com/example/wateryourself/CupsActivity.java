package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class CupsActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    Integer[] colors = null;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cups);
    }
}
