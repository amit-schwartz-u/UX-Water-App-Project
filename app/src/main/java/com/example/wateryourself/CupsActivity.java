package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//todo: write total amount when choosing cups of water on third page

public class CupsActivity extends AppCompatActivity {

    public static final String NAME_STR = "STRING OF USERNAME";
    public static final String AMOUNT_OF_WATER = "Amount Of Water";
//    ViewPager viewPager;
//    Adapter adapter;
//    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
//    List<Model> models;
    ImageButton[] imagesAmountOfWater = new ImageButton[4];
//    ImageView[] littleImageViews = new ImageView[4];
    static String mName;
    int amountChosen=0;
//    int currentImage = 0;
//    int[] counters = {0, 0, 0, 0};
    int[] amounts = {200, 500, 750, 1000};
    Button mUpdtaeButton;
    Button mCleanAllButton;
//    TextView[] tv_display_amount = new TextView[4];

    // Shared preferences object
    private SharedPreferences mPreferences;

    // Name of shared preferences file
    private String waterYourselfFile =
            "com.example.android.waterYourselfprefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cups);
        fillImageButtonsAmountOfWater();
        Intent intent = getIntent();
        updateUserName(intent);
        mUpdtaeButton =  findViewById(R.id.update_button);
        mCleanAllButton = findViewById(R.id.clean_all_btn);
        changeBtnMode(mUpdtaeButton,View.INVISIBLE);
        changeBtnMode(mCleanAllButton,View.INVISIBLE);

    }
    private void fillImageButtonsAmountOfWater() {

        imagesAmountOfWater[0] = findViewById(R.id.ib_cup200);
        imagesAmountOfWater[1] = findViewById(R.id.ib_cup500);
        imagesAmountOfWater[2] = findViewById(R.id.ib_bottle750);
        imagesAmountOfWater[3] = findViewById(R.id.ib_bottle1);


    }


    private void updateUserName(Intent intent) {
        if (intent.getStringExtra(MainActivity.FROM_MAIN).equals("LOGIN")) {
            mName = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

            final SharedPreferences reader = getApplicationContext().getSharedPreferences(waterYourselfFile, Context.MODE_PRIVATE);
            final SharedPreferences.Editor preferencesEditor = reader.edit();
            preferencesEditor.putString(NAME_STR, mName);
            preferencesEditor.apply();
        } else {
            mPreferences = getSharedPreferences(waterYourselfFile, MODE_PRIVATE);
            mName = mPreferences.getString(NAME_STR, mName);
        }
        TextView textView = findViewById(R.id.tv_hello_name);
        textView.setText(String.format("Hi %s !", mName));
    }

    private void changeBtnMode(Button btn,int mode) {
        btn.setVisibility(mode);
    }

//    private void makeInvisibleIV() {
//        littleImageViews[0] = (ImageView) findViewById(R.id.iv_littlecup200);
//        littleImageViews[1] = (ImageView) findViewById(R.id.iv_littlecup500);
//        littleImageViews[2] = (ImageView) findViewById(R.id.iv_littlebottle750);
//        littleImageViews[3] = (ImageView) findViewById(R.id.iv_littlebottle1);
//        for (int i = 0; i < littleImageViews.length; i++) {
//            littleImageViews[i].setVisibility(View.INVISIBLE);
//        }
//    }
//
//    public void addAmount(View view) {
//        changeBtnMode(View.VISIBLE);
//
//        //prevent user from choosing more than 3 of each cup/bottle
//        if (counters[currentImage]==3){
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "That's too much! You can't pick more than 3",
//                    Toast.LENGTH_LONG);
//            toast.show();
//        }
//
//        else{
//            counters[currentImage]++;
//        }
//        if (counters[currentImage] == 1) {
//            littleImageViews[currentImage].setVisibility(View.VISIBLE);
//        }
//        tv_display_amount[currentImage].setText(Integer.toString(counters[currentImage]));
//        Button updateButton = findViewById(R.id.update_button);
//        updateButton.setVisibility(View.VISIBLE);
//        int totalAmountChosen = 0; //todo: reuse of this value also in launch daily
//        for (int i = 0; i < counters.length; i++) {
//            totalAmountChosen += counters[i] * amounts[i];
//        }
//        TextView totalAmountText = findViewById(R.id.tv_total_amount_chosen);
//        totalAmountText.setText(String.format("Total Amount Chosen: %s ML", Integer.toString(totalAmountChosen)));
//
//    }

    public void cleanAmountChosen(View view) {
//        for (int i = 0; i < counters.length; i++) {
//            counters[i] = 0;
//            littleImageViews[i].setVisibility(View.INVISIBLE);
//            tv_display_amount[i].setText("");
//        }
        changeBtnMode(mCleanAllButton,View.INVISIBLE);
        changeBtnMode(mUpdtaeButton,View.INVISIBLE);
        TextView totalAmountText = findViewById(R.id.tv_total_amount_chosen);
        totalAmountText.setText(String.format("Amount Chosen: 0 ML"));
    }

    public void launchDailyActivity(View view) {


        Intent intent = new Intent(this, DailyActivity.class);
        intent.putExtra(AMOUNT_OF_WATER, amountChosen);
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }

    @Override
    public void onBackPressed() {

    }

    public void addAmountOfWater(View view) {
        ImageButton amountChosenImg = (ImageButton)view;

        for (int i =0;i<imagesAmountOfWater.length;i++){
            if (amountChosenImg == imagesAmountOfWater[i]){
                amountChosen = amounts[i];
            }
        }
        TextView totalAmountText = findViewById(R.id.tv_total_amount_chosen);
        totalAmountText.setText(String.format("Amount Chosen: %s ML", Integer.toString(amountChosen)));
        changeBtnMode(mCleanAllButton,View.VISIBLE);
        changeBtnMode(mUpdtaeButton,View.VISIBLE);


    }
}
