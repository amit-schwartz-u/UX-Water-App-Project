package com.example.wateryourself;

import android.content.Context;
import android.content.SharedPreferences;

class MyPreferences {
    private static final String MY_PREFERENCES = "myPreferences";

    public static boolean isFirst(Context context) {
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final boolean first = reader.getBoolean("is_first", true);
        return first;
    }
}
