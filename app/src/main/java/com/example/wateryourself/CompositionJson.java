package com.example.wateryourself;

import android.content.res.Resources;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompositionJson extends JSONObject{

    public JSONObject makeJSONObject (String name, String age, String weight, String height) {

        JSONObject obj = new JSONObject() ;
        JSONObject jsonAddUser = new JSONObject();

        try {
            jsonAddUser.put("name", name);
            jsonAddUser.put("ageEditText", age);
            jsonAddUser.put("weight", weight);
            jsonAddUser.put("height", height);
            obj.put("curUser", jsonAddUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
