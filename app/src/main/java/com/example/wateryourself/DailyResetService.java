package com.example.wateryourself;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;


public class DailyResetService extends IntentService {
    private static final String CUR_WATER_AMOUNT = "CURRENT WATER AMOUNT";
    // Name of shared preferences file
    private String waterYourselfFile =
            "com.example.android.waterYourselfprefs";

    /**
     * A constructor is required, and must call the super <code><a href="/reference/android/app/IntentService.html#IntentService(java.lang.String)">IntentService(String)</a></code>
     * constructor with a name for the worker thread.
     */
    public DailyResetService() {
        super("com.example.wateryourself.DailyResetService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            final SharedPreferences reader = getApplicationContext().getSharedPreferences(waterYourselfFile, Context.MODE_PRIVATE);
            final SharedPreferences.Editor preferencesEditor = reader.edit();
            preferencesEditor.putInt(CUR_WATER_AMOUNT, 0);
            preferencesEditor.apply();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "amit !!!!!! ",
                    Toast.LENGTH_LONG);
            toast.show();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}