package com.example.lammel.lob;

import android.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by stina on 04.07.17.
 */

public class NotificationService extends Service {

    private final static String TAG = "NotificationService";
    private String text;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;

    // Unique id to identify the notification.
    private static final int NOTIFICATION = 002;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFYSERVICE = "com.example.lammel.lob.INTENT_NOTIFYSERVICE";
    Intent bi = new Intent(INTENT_NOTIFYSERVICE);
    // The system notification manager
    private NotificationManager mNM;
    private Calendar calendar;
    private Boolean yes;

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate()");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        calendar = Calendar.getInstance();
        saved = getSharedPreferences(PREFS_NAME, 0);
        text = saved.getString("Notification", "");
        yes = false;
        Log.i(TAG, "Tag: " + calendar.get(Calendar.DAY_OF_WEEK));
        switch(calendar.get(Calendar.DAY_OF_WEEK)){

            case 1:
                if(saved.getBoolean("Sunday", false)){
                    yes = true;
                    showNotification();
                }
                break;
            case 2:
                if(saved.getBoolean("Monday", false)) {
                    yes = true;
                    showNotification();
                }
                break;
            case 3:
                if(saved.getBoolean("Tuesday", false)) {
                    yes = true;
                    showNotification();
                }
                break;
            case 4:
                if(saved.getBoolean("Wednesday", false)) {
                    yes = true;
                    showNotification();
                }
                break;
            case 5:
                if(saved.getBoolean("Thursday", false)) {
                    yes = true;
                    showNotification();
                }
                break;
            case 6:
                if(saved.getBoolean("Friday", false)) {
                    yes = true;
                    showNotification();
                }
                break;
            case 7:
                if(saved.getBoolean("Saturday", false)) {
                    yes = true;
                    showNotification();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // If this service was started by out AlarmTask intent then we want to show our notification
        return super.onStartCommand(intent, flags, startId);
    }



    /**
     * Creates a notification and shows it in the OS drag-down status bar (personal Notification)
     */
    private void showNotification() {
        if(yes) {
            Log.i(TAG, "Timer abgelaufen");
            PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
            Resources r = getResources();
            android.app.Notification notification = new NotificationCompat.Builder(this)
                    .setTicker(r.getString(R.string.notification_title))
                    .setSmallIcon(R.mipmap.notificationicon)
                    .setContentTitle(r.getString(R.string.notification_title))
                    .setContentText(text)
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();

            // Send the notification to the system.
            mNM.notify(NOTIFICATION, notification);
        }

        // Stop the service when we are finished
        stopSelf();
    }


    @Override
    public void onDestroy(){
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
