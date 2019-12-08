package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final int MAXIMUM_WEIGHT = 200;
    private static final int MINIMUM_WEIGHT = 6;
    private static final int MAXIMUM_HEIGHT = 250;
    private static final int MINIMUM_HEIGHT = 50;
    private static final int MAXIMUM_LENGTH = 10;
    private static final int MAXIMUM_AGE = 120;
    private static final int MINIMUM_AGE = 6;
    private static final int NAME_MAXIMUM_LENGTH = 18;
    public static final String LOGIN_SRC = "LOGIN";
    private EditText ageEditText;
    private EditText weightEditText;
    private EditText mEnterNameEditText;
    private EditText heightEditText;
    private static final String CUR_WATER_AMOUNT = "CURRENT WATER AMOUNT";

    public static final String EXTRA_MESSAGE
            = "com.example.android.WaterYourself.extra.MESSAGE";
    private Boolean isUserNameValid;
    private Boolean isUserAgeValid;
    private Boolean isUserWeightValid;
    private Boolean isUserHeightValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validateUserInput();
        mEnterNameEditText = (EditText) findViewById(R.id.et_enter_name);
        isUserNameValid = false;
        isUserAgeValid = false;
        isUserWeightValid = false;
        isUserHeightValid = false;
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
                isUserNameValid = false;
                if (start >= NAME_MAXIMUM_LENGTH) {
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
                isUserAgeValid = false;
                if (start >= 4) {
                    ageEditText.setError("Maximum Limit Reached!");
                }
                if (ageEditText.getText().toString().length() != 0) {
                    int curAge = Integer.parseInt(ageEditText.getText().toString());
                    if (curAge <= MAXIMUM_AGE && curAge >= MINIMUM_AGE) {
                        isUserAgeValid = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                isUserAgeValid = false;
                if (ageEditText.getText().toString().length() != 0) {
                    int curAge = Integer.parseInt(ageEditText.getText().toString());
                    if (curAge <= MAXIMUM_AGE && curAge >= MINIMUM_AGE) {
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
                    if (curAge > MAXIMUM_AGE) {
                        ageEditText.setError("age is to old!");
                    } else if (curAge < MINIMUM_AGE) {
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
                isUserWeightValid = false;
                if (start >= 10) {
                    weightEditText.setError("Maximum Limit Reached!");
                }
                if (weightEditText.getText().toString().length() != 0) {
                    int curWeight = Integer.parseInt(weightEditText.getText().toString());
                    if (curWeight <= MAXIMUM_WEIGHT && curWeight >= MINIMUM_WEIGHT) {
                        isUserWeightValid = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                isUserWeightValid = false;
                if (weightEditText.getText().toString().length() != 0) {
                    int curWeight = Integer.parseInt(weightEditText.getText().toString());
                    if (curWeight <= MAXIMUM_WEIGHT && curWeight >= MINIMUM_WEIGHT) {
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
                    if (curWeight > LoginActivity.MAXIMUM_WEIGHT) {
                        weightEditText.setError("weight is too big!");
                    } else if (curWeight < MINIMUM_WEIGHT) {
                        weightEditText.setError("weight is too low!");
                    } else {
                        isUserWeightValid = true;
                    }
                }
            }
        });
    }

    private void validateHeight() {
        heightEditText = (EditText) findViewById(R.id.et_enter_height);
        heightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserHeightValid = false;
                if (start >= MAXIMUM_LENGTH) {
                    heightEditText.setError("Maximum Limit Reached!");
                }
                if (heightEditText.getText().toString().length() != 0) {
                    int curHeight = Integer.parseInt(heightEditText.getText().toString());
                    if (curHeight <= MAXIMUM_HEIGHT && curHeight >= MINIMUM_HEIGHT) {
                        isUserHeightValid = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                isUserHeightValid = false;
                if (heightEditText.getText().toString().length() != 0) {
                    int curHeight = Integer.parseInt(heightEditText.getText().toString());
                    if (curHeight <= MAXIMUM_HEIGHT && curHeight >= MINIMUM_HEIGHT) {
                        isUserHeightValid = true;
                    }
                }
            }
        });
        heightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (heightEditText.getText().toString().length() == 0) {
                        heightEditText.setError("height is required!");
                        return;
                    }
                    int curWeight = Integer.parseInt(heightEditText.getText().toString());
                    if (curWeight > MAXIMUM_HEIGHT) {
                        heightEditText.setError("max height is 250!");
                    } else if (curWeight < MINIMUM_HEIGHT) {
                        heightEditText.setError("minimum height is 50!");
                    } else {
                        isUserWeightValid = true;
                    }
                }
            }
        });
    }

    public void checkIfShouldLunchCupsActivity(View view) {
        if (isUserInputValid()) {
            launchCupsActivity();
        } else {
            Toast myToast = Toast.makeText(this, "please fill all fields", Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP | Gravity.LEFT, 300, 300);
            ViewGroup toastView = (ViewGroup) myToast.getView();
            TextView tv = (TextView) toastView.getChildAt(0);
            tv.setTextSize(18);
            myToast.show();
        }
    }

    public boolean isUserInputValid() {
        if (isUserNameValid && isUserAgeValid && isUserWeightValid && isUserHeightValid) {
            return true;
        }
        return false;
    }

    public void launchCupsActivity() {
        final SharedPreferences reader = getApplicationContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putBoolean(MyPreferences.IS_FIRST_LOGIN, false);
        preferencesEditor.putInt(CUR_WATER_AMOUNT, 0);
        preferencesEditor.apply();
        Intent intent = new Intent(this, CupsActivity.class);
        String name = mEnterNameEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, name);
        intent.putExtra(MainActivity.FROM, LOGIN_SRC);
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }
}
