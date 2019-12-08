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


public class CupsActivity extends AppCompatActivity {

    public static final String NAME_STR = "STRING OF USERNAME";
    public static final String AMOUNT_OF_WATER = "Amount Of Water";
    ImageButton[] imagesAmountOfWater = new ImageButton[4];
    int[] pickedAmmountImg = new int[4];
    int[] unpickedAmmountImg = new int[4];
    static String mName;
    int amountChosen=0;
    int[] amounts = {200, 500, 750, 1000};

    Button mUpdateButton;
    Button mSkipButton;

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
        fillImagesId();
        Intent intent = getIntent();
        updateUserName(intent);
        mUpdateButton =  findViewById(R.id.update_button);
        mSkipButton = findViewById(R.id.skip_to_my_plant_btn);
        changeBtnMode(mUpdateButton,View.INVISIBLE);
        changeBtnMode(mSkipButton,View.VISIBLE);

    }
    private void fillImageButtonsAmountOfWater() {

        imagesAmountOfWater[0] = findViewById(R.id.ib_cup200);
        imagesAmountOfWater[1] = findViewById(R.id.ib_cup500);
        imagesAmountOfWater[2] = findViewById(R.id.ib_bottle750);
        imagesAmountOfWater[3] = findViewById(R.id.ib_bottle1);

    }

    private void fillImagesId() {
        pickedAmmountImg[0] = R.drawable.chosen_cup200;
        pickedAmmountImg[1] = R.drawable.chosen_cup500;
        pickedAmmountImg[2] = R.drawable.chosen_bottle750;
        pickedAmmountImg[3] = R.drawable.chosen_bottle1;

        unpickedAmmountImg[0] = R.drawable.cup200;
        unpickedAmmountImg[1] = R.drawable.cup500;
        unpickedAmmountImg[2] = R.drawable.bottle750;
        unpickedAmmountImg[3] = R.drawable.bottle1;

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

    public void skipToPlant(View view) {
        launchDailyActivity(view);
    }

    public void launchDailyActivity(View view) {

        Intent intent = new Intent(this, DailyActivity.class);
        intent.putExtra(AMOUNT_OF_WATER, amountChosen);
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    public void addAmountOfWater(View view) {
        ImageButton amountChosenImg = (ImageButton)view;

        for (int i =0;i<imagesAmountOfWater.length;i++){
            if (amountChosenImg == imagesAmountOfWater[i]){
                amountChosen = amounts[i];
                amountChosenImg.setImageDrawable(getResources().getDrawable(pickedAmmountImg[i]));
            }
            else
            {
                imagesAmountOfWater[i].setImageDrawable(getResources().getDrawable(unpickedAmmountImg[i]));
            }
        }
        changeBtnMode(mSkipButton,View.INVISIBLE);
        changeBtnMode(mUpdateButton,View.VISIBLE);

    }
}
