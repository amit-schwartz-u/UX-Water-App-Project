package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;

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
        Intent intent = new Intent(this, CupsActivity.class); //todo change this?
        startActivityForResult(intent, MainActivity.TEXT_REQUEST);
    }

    private void writeToJsonFile() {
        EditText name = (EditText) findViewById(R.id.et_enter_name);
        EditText dateOfBirth = (EditText) findViewById(R.id.et_enter_birthday);
        EditText weight = (EditText) findViewById(R.id.et_enter_weight);
        EditText height = (EditText) findViewById(R.id.et_enter_height);
        CompositionJson compositionJso = new CompositionJson();
        JSONObject jsonObject = compositionJso.makeJSONObject(name.getText().toString(), dateOfBirth.getText().toString(),weight.getText().toString(), height.getText().toString());
        //todo gender
        try {
            File file = new File("storage/sdcard/MyIdea/MyCompositions/" + compoTitle + ".json");
            Writer output = new BufferedWriter(new FileWriter(file));
            output.write(jsonObject.toString());
            output.close();

        }
        catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
