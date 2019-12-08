package com.example.wateryourself;

import android.content.Context;
import android.content.SharedPreferences;

class MyPreferences {
    public static final String MY_PREFERENCES = "myPreferences";
    public static final String IS_FIRST_LOGIN = "isFirst";
    public static final String CUMULATIVE_WATER_AMOUNT = "cumulativeWaterAmount";

    public static boolean isFirst(Context context) {
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final boolean first = reader.getBoolean("isFirst", true);
        return first;
    }
}
