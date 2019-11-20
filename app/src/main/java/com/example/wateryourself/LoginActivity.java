package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onRadioButtonClicked(View view) {
        //todo
    }

    public void launchCupsActivity(View view) {
        writeToJsonFile();
        final SharedPreferences reader = getApplicationContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = reader.edit();
        editor.putBoolean("is_first", false);
        editor.commit();
        Intent intent = new Intent(this, CupsActivity.class); //todo change this?
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }

    private void writeToJsonFile() {
        EditText name = (EditText) findViewById(R.id.et_enter_name);
        EditText age = (EditText) findViewById(R.id.et_enter_age);
        EditText weight = (EditText) findViewById(R.id.et_enter_weight);
        EditText height = (EditText) findViewById(R.id.et_enter_height);
        CompositionJson compositionJso = new CompositionJson();
        JSONObject jsonObject = compositionJso.makeJSONObject(name.getText().toString(), age.getText().toString(), weight.getText().toString(), height.getText().toString());
        //todo gender

        try {
            String fileName ="login.json";
            File file = new File(getApplicationContext().getFilesDir(),fileName);
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
