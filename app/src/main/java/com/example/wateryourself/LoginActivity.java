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
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText ageEditText;
    private EditText weightEditText;
    private EditText mEnterNameEditText;
    private static final String CUR_WATER_AMOUNT = "CURRENT WATER AMOUNT";

    public static final String EXTRA_MESSAGE
            = "com.example.android.WaterYourself.extra.MESSAGE";
    private Boolean isUserNameValid;
    private Boolean isUserAgeValid;
    private Boolean isUserWeightValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validateUserInput();
        mEnterNameEditText = (EditText) findViewById(R.id.et_enter_name);
        isUserNameValid = false;
        isUserAgeValid = false;
        isUserWeightValid = false;
    }

    private void validateUserInput() {
        validateAge();
        validateName();
        validateWeight();
        validateHeight();
    }

    private void validateName() {
        mEnterNameEditText = (EditText) findViewById(R.id.et_enter_name);
        mEnterNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start >= 18) {
                    mEnterNameEditText.setError("Maximum Limit Reached!");
                } else if (mEnterNameEditText.getText().toString().length() == 0) {
                    mEnterNameEditText.setError("name is required!");
                } else {
                    isUserNameValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEnterNameEditText.getText().toString().length() == 0) {
                    isUserNameValid = false;
                }
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
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ageEditText.getText().toString().length() != 0) {
                    int curAge = Integer.parseInt(ageEditText.getText().toString());
                    if (curAge <= 120 && curAge >= 6) {
                        isUserAgeValid = true;
                    }
                }
            }
        });
        ageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (ageEditText.getText().toString().length() == 0) {
                        ageEditText.setError("age is required!");
                        return;
                    }
                    int curAge = Integer.parseInt(ageEditText.getText().toString());
                    if (curAge > 120) {
                        ageEditText.setError("age is to old!");
                    } else if (curAge < 6) {
                        ageEditText.setError("age is to young!");
                    } else {
                        isUserAgeValid = true;
                    }
                }
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
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (weightEditText.getText().toString().length() != 0) {
                    int curWeight = Integer.parseInt(weightEditText.getText().toString());
                    if (curWeight <= 150 && curWeight >= 6) {
                        isUserWeightValid = true;
                    }
                }
            }
        });
        weightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (weightEditText.getText().toString().length() == 0) {
                        weightEditText.setError("weight is required!");
                        return;
                    }
                    int curWeight = Integer.parseInt(weightEditText.getText().toString());
                    if (curWeight > 150) {
                        weightEditText.setError("weight is too big!");
                    } else if (curWeight < 6) {
                        weightEditText.setError("weight is too low!");
                    } else {
                        isUserWeightValid = true;
                    }
                }
            }
        });
    }

    private void validateHeight() {
    }


    public void onRadioButtonClicked(View view) {
        //todo
    }

    public void checkIfShouldLunchCupsActivity(View view) {
        if (isUserInputValid()) {
            launchCupsActivity();
        } else { //todo put in place
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Invalid user input!",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public boolean isUserInputValid() {
        if (isUserNameValid && isUserAgeValid && isUserWeightValid) {
            return true;
        }
        return false;
    }

    public void launchCupsActivity() {
        final SharedPreferences reader = getApplicationContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putBoolean("is_first", false);
        preferencesEditor.putBoolean("is_today_first_time_to_meet_daily_goal", true);
        preferencesEditor.putInt(CUR_WATER_AMOUNT, 0);
        preferencesEditor.apply();
        Intent intent = new Intent(this, CupsActivity.class);
        String name = mEnterNameEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, name);
        intent.putExtra(MainActivity.FROM_MAIN, "LOGIN");
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }
}
