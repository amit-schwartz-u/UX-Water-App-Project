package com.example.wateryourself;

import android.content.res.Resources;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompositionJson extends JSONObject{

    public JSONObject makeJSONObject (String name, String dateOfBirth, String weight, String height) {

        JSONObject obj = new JSONObject() ;

        try {
            obj.put("name", name);
            obj.put("dateOfBirth", dateOfBirth);
            obj.put("weight", weight);
            obj.put("height", height);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
