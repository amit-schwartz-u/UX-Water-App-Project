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

/**
 * this is the login activity. The user is required to fill some details about himself, like name, age,
 * gender etc. in order for us to provide the best drinking daily program. We also included a validation
 * check for the user inputs.
 * This activity will only be presented on the first time the user enters the app.
 */
public class LoginActivity extends AppCompatActivity {
    /* constants */
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
    public static final String EXTRA_MESSAGE = "com.example.android.WaterYourself.extra.MESSAGE";
    private Boolean isUserNameValid;
    private Boolean isUserAgeValid;
    private Boolean isUserWeightValid;
    private Boolean isUserHeightValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validateUserInput();
        mEnterNameEditText = findViewById(R.id.et_enter_name);
        isUserNameValid = false;
        isUserAgeValid = false;
        isUserWeightValid = false;
        isUserHeightValid = false;
    }

    /**
     * validating all fields filled by the user
     */
    private void validateUserInput() {
        validateAge();
        validateName();
        validateWeight();
        validateHeight();
    }

    /**
     * validate the entered name.
     */
    private void validateName() {
        mEnterNameEditText = findViewById(R.id.et_enter_name);
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

    /**
     * validating the age entered. Age has to be between 6 and 120.
     */
    private void validateAge() {
        ageEditText = findViewById(R.id.et_enter_age);
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
                        ageEditText.setError("age is too old!");
                    } else if (curAge < MINIMUM_AGE) {
                        ageEditText.setError("age is too young!");
                    } else {
                        isUserAgeValid = true;
                    }
                }
            }
        });
    }

    /**
     * validating the weight entered. Weight has to be between 6 and 200.
     */
    private void validateWeight() {
        weightEditText = findViewById(R.id.et_enter_weight);
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

    /**
     * validating the height entered. Height has to be between 50 and 250.
     */
    private void validateHeight() {
        heightEditText = findViewById(R.id.et_enter_height);
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

    /**
     * checks if the next activity should be launched, according to user inputs.
     */
    public void checkIfShouldLunchCupsActivity(View view) {
        if (isUserInputValid()) {
            launchCupsActivity();
        } else {
            Toast myToast = Toast.makeText(this, "please make sure all fields are filled correctly :)", Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 300);
            ViewGroup toastView = (ViewGroup) myToast.getView();
            TextView tv = (TextView) toastView.getChildAt(0);
            tv.setTextSize(14);
            myToast.show();
        }
    }

    /**
     * checks if all user inputs are valid.
     */
    public boolean isUserInputValid() {
        if (isUserNameValid && isUserAgeValid && isUserWeightValid && isUserHeightValid) {
            return true;
        }
        return false;
    }

    /**
     * launching the cups activity after the user filled all login fields correctly. All the filled
     * data is saved to preferences and the name is transferred to the next activity.
     */
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
