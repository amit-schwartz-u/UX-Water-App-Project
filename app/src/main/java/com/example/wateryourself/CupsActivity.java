package com.example.wateryourself;

import androidx.annotation.RequiresApi;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CupsActivity extends AppCompatActivity {

    public static final String NAME_STR = "STRING OF USERNAME";
    public static final String AMOUNT_OF_WATER = "Amount Of Water";
    ViewPager viewPager;
    Adapter adapter;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<Model> models;
    ImageView[] littleImageViews = new ImageView[4];
    static String mName;
    int currentImage = 0;
    int[] counters = {0, 0, 0, 0};
    int[] amounts = {200, 500, 750, 1000};
    TextView[] tv_display_amount = new TextView[4];
    int chosenWaterAmount; //todo delete

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
        changeCleanAllBtnMode(View.INVISIBLE);
        Intent intent = getIntent();
        updateUserName(intent);
        fillTextViewCountAmmount();
        makeInvisibleIV();

        models = new ArrayList<>();
        models.add(new Model(R.drawable.cup200));
        models.add(new Model(R.drawable.cup500));
        models.add(new Model(R.drawable.bottle750));
        models.add(new Model(R.drawable.bottle1));


        adapter = new Adapter(models, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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

    private void fillTextViewCountAmmount() {

        tv_display_amount[0] = findViewById(R.id.tv_count_200);

        tv_display_amount[1] = findViewById(R.id.tv_count_500);

        tv_display_amount[2] = findViewById(R.id.tv_count_750);

        tv_display_amount[3] = findViewById(R.id.tv_count_1000);

    }

    private void updateUserName(Intent intent) {
        if (intent.getStringExtra(MainActivity.FROM_MAIN).equals("LOGIN")) {
            mName = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

            final SharedPreferences reader = getApplicationContext().getSharedPreferences(waterYourselfFile, Context.MODE_PRIVATE);
            final SharedPreferences.Editor preferencesEditor = reader.edit();
//            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putString(NAME_STR, mName);
            preferencesEditor.apply();
        } else {

            mPreferences = getSharedPreferences(waterYourselfFile, MODE_PRIVATE);
            mName = mPreferences.getString(NAME_STR, mName);

        }
        TextView textView = findViewById(R.id.tv_hello_name);
        textView.setText(String.format("Hi %s !", mName));
    }

    private void changeCleanAllBtnMode(int mode) {
        Button cleanAll = findViewById(R.id.clean_all_btn);
        cleanAll.setVisibility(mode);
    }

    private void makeInvisibleIV() {
        littleImageViews[0] = (ImageView) findViewById(R.id.iv_littlecup200);
        littleImageViews[1] = (ImageView) findViewById(R.id.iv_littlecup500);
        littleImageViews[2] = (ImageView) findViewById(R.id.iv_littlebottle750);
        littleImageViews[3] = (ImageView) findViewById(R.id.iv_littlebottle1);
        for (int i = 0; i < littleImageViews.length; i++) {
            littleImageViews[i].setVisibility(View.INVISIBLE);
        }
    }


//    private void setUserName() { //todo use var userName
//        try {
//            InputStream is = openFileInput("login.json");
//            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//            BufferedReader bufferedReader = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                sb.append(line);
//            }
//            JSONObject reader = new JSONObject(sb.toString());
//            JSONObject user = reader.getJSONObject("curUser");
//            userName = user.getString("name");
//        } catch (JSONException e) { //todo change exceptions
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void addAmount(View view) {
        changeCleanAllBtnMode(View.VISIBLE);
        counters[currentImage]++;
        if (counters[currentImage] == 1) {
            littleImageViews[currentImage].setVisibility(View.VISIBLE);
        }
        tv_display_amount[currentImage].setText(Integer.toString(counters[currentImage]));



    }

    public void cleanAmountChosen(View view) {
        for (int i = 0; i < counters.length; i++) {
            counters[i] = 0;
            littleImageViews[i].setVisibility(View.INVISIBLE);
            tv_display_amount[i].setText("");
        }
        changeCleanAllBtnMode(View.INVISIBLE);

    }

    public void launchDailyActivity(View view) {
        int totalAmountChosen = 0;
        for (int i = 0; i < counters.length; i++) {
            totalAmountChosen += counters[i] * amounts[i];
        }

        Intent intent = new Intent(this, DailyActivity.class); //todo change this?
        intent.putExtra(AMOUNT_OF_WATER, totalAmountChosen);
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }

}
