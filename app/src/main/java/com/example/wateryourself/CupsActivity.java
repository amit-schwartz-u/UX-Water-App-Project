package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CupsActivity extends AppCompatActivity {

    private static final String NAME_STR = "STRING OF USERNAME";
    ViewPager viewPager;
    Adapter adapter;
    Drawable[] backgrounds = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<Model> models;

    String mName;
    int currentImage = 0;
    int[] counters = {0, 0, 0, 0};

    int currentWaterAmount; 

    String userName;


    // Shared preferences object
    private SharedPreferences mPreferences;

    // Name of shared preferences file
    private String waterYourselfFile =
            "com.example.android.waterYourselfprefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cups);

        Intent intent = getIntent();
        if (intent.getStringExtra(MainActivity.FROM_MAIN).equals("LOGIN")) {
            mName = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

            final SharedPreferences reader = getApplicationContext().getSharedPreferences(waterYourselfFile, Context.MODE_PRIVATE);
            final SharedPreferences.Editor preferencesEditor = reader.edit();
//            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putString(NAME_STR, mName);
            preferencesEditor.apply();
        } else {

            mPreferences = getSharedPreferences(waterYourselfFile, MODE_PRIVATE);
            mName =mPreferences.getString(NAME_STR,mName);

        }
        TextView textView = findViewById(R.id.tv_hello_name);
        textView.setText(String.format("Welcome %s !!", mName));



        setUserName();
        setCurrentWaterAmount();

        models = new ArrayList<>();
        models.add(new Model(R.drawable.cup200));
        models.add(new Model(R.drawable.cup500));
        models.add(new Model(R.drawable.bottle750));
        models.add(new Model(R.drawable.bottle1));


        adapter = new Adapter(models, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Drawable[] background_temp = {getResources().getDrawable(R.drawable.back1),
                getResources().getDrawable(R.drawable.back2),
                getResources().getDrawable(R.drawable.back3),
                getResources().getDrawable(R.drawable.back4)};

        backgrounds = background_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (backgrounds.length - 1)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        viewPager.setBackground(backgrounds[position]);
                    }
//                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset,
//                            backgrounds[position], backgrounds[position + 1]));
                } else {
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        viewPager.setBackground(backgrounds[backgrounds.length - 1]);
                    }
                }
                currentImage = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
            currentWaterAmount = reader.getInt("currentWaterAmount");
        } catch (JSONException e) { //todo change exceptions
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUserName() { //todo use var userName
        try {
            InputStream is = openFileInput("login.json");
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject reader = new JSONObject(sb.toString());
            JSONObject user = reader.getJSONObject("curUser");
            userName = user.getString("name");
        } catch (JSONException e) { //todo change exceptions
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAmount(View view) {
        counters[currentImage]++;

        //todo: delete dis shit
        Toast toast = Toast.makeText(getApplicationContext(),
                String.valueOf(counters[currentImage]), Toast.LENGTH_SHORT);
        toast.show();
        //



    }
}
