package com.example.lammel.lob;

import android.app.AlarmManager;
import android.app.Application;
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
    // Context to retrieve the alarm manager from
    private final Context context ;
    private long countdown = 30000;

    private final long time;

    public AlarmTask(Context context) {

        Log.i(TAG, "start timer..");
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.time = System.currentTimeMillis() + countdown;

    }

    public AlarmTask(Context context, long countdown){
        Log.i(TAG, "start timer..");
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.time = System.currentTimeMillis() + countdown;
    }

    @Override
    public void run() {

        //Adress the AlarmService.class to handle the Notification if time is up
        Intent intent = new Intent(context, AlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);

        // Sets an alarm
        am.set(AlarmManager.RTC, time, pendingIntent);
    }

    public void cancelAlarm(){
        Intent intent = new Intent(context, AlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        am.cancel(pendingIntent);

    }
}
