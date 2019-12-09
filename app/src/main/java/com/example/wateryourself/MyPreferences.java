package com.example.wateryourself;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * this class is responsible for saving app state and date to preferences file.
 */
class MyPreferences {
    static final String MY_PREFERENCES = "myPreferences";
    static final String IS_FIRST_LOGIN = "isFirst";
    static final String CUMULATIVE_WATER_AMOUNT = "cumulativeWaterAmount";

    static boolean isFirst(Context context) {
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        return reader.getBoolean("isFirst", true);
    }
}
