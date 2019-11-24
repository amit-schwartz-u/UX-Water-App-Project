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
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;


public class LoginActivity extends AppCompatActivity{
    TextView output;
    EditText age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        output = (TextView)findViewById(R.id.tv_age);
        age = (EditText)findViewById(R.id.et_enter_age);
        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                output.setText(s);
                if (start == 12) {
                    Toast.makeText(getApplicationContext(), "Maximum Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        //todo
    }

    public void launchCupsActivity(View view) {
        writeLoginUserInputToJsonFile();
        setDrinkingStatusFile();
        final SharedPreferences reader = getApplicationContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = reader.edit();
        editor.putBoolean("is_first", false);
        editor.commit();
        Intent intent = new Intent(this, CupsActivity.class); //todo change this?
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }

    private void setDrinkingStatusFile() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("currentWaterAmount", "0");
            String fileName = "drinkingStatus.json";
            File file = new File(getApplicationContext().getFilesDir(), fileName);
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            if (jsonObject != null) {
                fos.write(jsonObject.toString().getBytes());
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeLoginUserInputToJsonFile() {
        EditText name = (EditText) findViewById(R.id.et_enter_name);
        EditText age = (EditText) findViewById(R.id.et_enter_age);
        EditText weight = (EditText) findViewById(R.id.et_enter_weight);
        EditText height = (EditText) findViewById(R.id.et_enter_height);
        CompositionJson compositionJso = new CompositionJson();
        JSONObject jsonObject = compositionJso.makeJSONObject(name.getText().toString(), age.getText().toString(), weight.getText().toString(), height.getText().toString());
        //todo gender

        try {
            String fileName = "login.json";
            File file = new File(getApplicationContext().getFilesDir(), fileName);
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            if (jsonObject != null) {
                fos.write(jsonObject.toString().getBytes());
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
