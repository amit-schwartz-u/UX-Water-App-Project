package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    public static final String FROM_MAIN = "called from main"; //todo from

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences reader = getApplicationContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = reader.edit();
        editor.putBoolean("is_first", true);
        editor.apply();
        //Time to launch the another activity
        int TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = null;
                boolean isFirstRun = MyPreferences.isFirst(MainActivity.this);
                if (isFirstRun) {
                    //show start activity
                    i = new Intent(MainActivity.this, LoginActivity.class);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY),
                            23,
                            0);
                    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                    PendingIntent pi = PendingIntent.getService(MainActivity.this, 0,
                            intent, 0);

                    am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                            60000, pi);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "alarm set !!!!!! ",
                            Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    i = new Intent(MainActivity.this, CupsActivity.class);
                    i.putExtra(MainActivity.FROM_MAIN, "MAIN");
                }
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
