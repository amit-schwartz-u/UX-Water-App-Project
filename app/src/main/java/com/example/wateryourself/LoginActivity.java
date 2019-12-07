package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText ageEditText;
    EditText nameEditText;
    EditText weightEditText;
    private EditText mEnterNameEditText;
    private static final String CUR_WATER_AMOUNT = "CURRENT WATER AMOUNT";

    public static final String EXTRA_MESSAGE
            = "com.example.android.WaterYourself.extra.MESSAGE";
    // Name of shared preferences file
    private String waterYourselfFile =
            "com.example.android.waterYourselfprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validateUserInput();
        mEnterNameEditText = (EditText) findViewById(R.id.et_enter_name);

    }

    private void validateUserInput() {
        validateAge();
        validateName();
        validateWeight();
        validateHeight();
    }

    private void validateName() {
        nameEditText = (EditText) findViewById(R.id.et_enter_name);
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start >= 18) {
                    nameEditText.setError("Maximum Limit Reached!");
                }
                if (nameEditText.getText().toString().length() == 0) {
                    nameEditText.setError("name is required!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void validateAge() {
        ageEditText = (EditText) findViewById(R.id.et_enter_age);
        ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start >= 4) {
                    ageEditText.setError("Maximum Limit Reached!");
                }
                if (ageEditText.getText().toString().length() == 0) {
                    ageEditText.setError("age is required!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void validateWeight() {
        weightEditText = (EditText) findViewById(R.id.et_enter_weight);
        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start >= 10) {
                    weightEditText.setError("Maximum Limit Reached!");
                }
                if (weightEditText.getText().toString().length() == 0) {
                    weightEditText.setError("weight is required!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void validateHeight() {
    }



    public void onRadioButtonClicked(View view) {
        //todo
    }

    public void launchCupsActivity(View view) {
        final SharedPreferences reader = getApplicationContext().getSharedPreferences(waterYourselfFile, Context.MODE_MULTI_PROCESS);
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putBoolean("is_first", false).putInt(CUR_WATER_AMOUNT, 0);
        preferencesEditor.apply();
        Intent intent = new Intent(this, CupsActivity.class);
        String name = mEnterNameEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, name);
        intent.putExtra(MainActivity.FROM_MAIN, "LOGIN");
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }
}
