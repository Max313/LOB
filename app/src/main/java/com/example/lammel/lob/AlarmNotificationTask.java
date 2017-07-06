package com.example.lammel.lob;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by stina on 04.07.17.
 */

public class AlarmNotificationTask implements Runnable {

    private final static String TAG = "AlarmNotificationTask";

    // The android system alarm manager
    private final AlarmManager am;
    // Context to retrieve the alarm manager from
    private final Context context;
    private Calendar c;


    //if the next alarm is >= 24 hours
    public AlarmNotificationTask(Context context, Calendar calendar){
        Log.i(TAG, ">= 24");
        this.context = context;
        this.c = calendar;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }



    @Override
    public void run() {
        /*
        // Enable {@code SampleBootReceiver} to automatically restart the alarm when the
        // device is rebooted.
        ComponentName receiver = new ComponentName(context, MyBootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);*/

        //Adress the AlarmService.class to handle the Notification if time is up
        Intent intent = new Intent(context, NotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);

        //Sets an one-Time Alarm
        am.setRepeating(AlarmManager.RTC, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);






    }



    public void cancelAlarm(){
        Intent intent = new Intent(context, NotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        am.cancel(pendingIntent);

    }
}
