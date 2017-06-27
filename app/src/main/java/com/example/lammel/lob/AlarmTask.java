package com.example.lammel.lob;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AlarmTask implements Runnable {
    private final static String TAG = "Alarm Task";

    // The android system alarm manager
    private final AlarmManager am;
    // Your context to retrieve the alarm manager from
    private final Context context ;
    private long countdown = 30000;

    private final long time;

    public AlarmTask(Context context) {

        Log.i(TAG, "start timer..");
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.time = System.currentTimeMillis() + 30000;

    }

    public AlarmTask(Context context, long countdown){
        Log.i(TAG, "start timer..");
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.time = System.currentTimeMillis() + countdown;
    }

    @Override
    public void run() {


        // Request to start are service when the alarm date is upon us
        // We don't start an activity as we just want to pop up a notification into the system bar not a full activity
        Intent intent = new Intent(context, AlarmService.class);

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);

        // Sets an alarm - note this alarm will be lost if the phone is turned off and on again
        am.set(AlarmManager.RTC, time, pendingIntent);
    }
}
