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
    private static final int MAXIMUM_LENGTH = 4;
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
                    return;
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
                int inputLength = ageEditText.getText().toString().length();
                if (inputLength >= MAXIMUM_LENGTH) {
                    ageEditText.setError("Maximum Limit Reached!");
                    return;
                }
                if (inputLength != 0) {
                    int curAge = Integer.parseInt(ageEditText.getText().toString());
                    if (curAge <= MAXIMUM_AGE && curAge >= MINIMUM_AGE) {
                        isUserAgeValid = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int inputLength = ageEditText.getText().toString().length();
                    if (inputLength == 0) {
                        ageEditText.setError("age is required!");
                        return;
                    }
                    if (inputLength > MAXIMUM_LENGTH) {
                        ageEditText.setError("Maximum Limit Reached!");
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
                int inputLength = weightEditText.getText().toString().length();
                if (inputLength >= MAXIMUM_LENGTH) {
                    weightEditText.setError("Maximum Limit Reached!");
                    return;
                }
                if (inputLength != 0) {
                    int curWeight = Integer.parseInt(weightEditText.getText().toString());
                    if (curWeight <= MAXIMUM_WEIGHT && curWeight >= MINIMUM_WEIGHT) {
                        isUserWeightValid = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        weightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int inputLength = weightEditText.getText().toString().length();
                    if (inputLength == 0) {
                        weightEditText.setError("weight is required!");
                        return;
                    }
                    if (inputLength > MAXIMUM_LENGTH) {
                        weightEditText.setError("Maximum Limit Reached!");
                        return;
                    }
                    int curWeight = Integer.parseInt(weightEditText.getText().toString());
                    if (curWeight > MAXIMUM_WEIGHT) {
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
                int inputLength = heightEditText.getText().toString().length();
                if (inputLength >= MAXIMUM_LENGTH) {
                    heightEditText.setError("Maximum Limit Reached!");
                    return;
                }
                if (inputLength != 0) {
                    int curHeight = Integer.parseInt(heightEditText.getText().toString());
                    if (curHeight <= MAXIMUM_HEIGHT && curHeight >= MINIMUM_HEIGHT) {
                        isUserHeightValid = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        heightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int inputLength = heightEditText.getText().toString().length();
                    if (inputLength == 0) {
                        heightEditText.setError("height is required!");
                        return;
                    }
                    if (inputLength > MAXIMUM_LENGTH) {
                        heightEditText.setError("Maximum Limit Reached!");
                        return;
                    }
                    int curHeight = Integer.parseInt(heightEditText.getText().toString());
                    if (curHeight > MAXIMUM_HEIGHT) {
                        heightEditText.setError("max height is 250!");
                    } else if (curHeight < MINIMUM_HEIGHT) {
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
