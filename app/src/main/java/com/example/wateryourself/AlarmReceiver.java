package com.example.wateryourself;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mp= MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mp.start();
//        Intent serviceIntent = new Intent(context, DailyResetService.class);
//        context.startService(serviceIntent);
//        Toast toast = Toast.makeText(context,"got here !!!!!! ",
//                Toast.LENGTH_LONG); //todo delete
//        toast.show();
    }
}