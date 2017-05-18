package com.example.lammel.lob;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;



public class AlarmService extends Service {

    private final static String TAG = "AlarmService";
    private int source;



    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;

    // Unique id to identify the notification.
    private static final int NOTIFICATION = 001;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFY = "com.example.lammel.lob.INTENT_NOTIFY";
    Intent bi = new Intent(INTENT_NOTIFY);
    // The system notification manager
    private NotificationManager mNM;

    @Override
    public void onCreate() {
        Log.i("NotifyService", "onCreate()");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        saved = getSharedPreferences(PREFS_NAME, 0);
        source = saved.getInt("id",0);

        // If this service was started by out AlarmTask intent then we want to show our notification
        if(source == 0){
            Log.i(TAG, "Version 1");
            showNotification();
        }

        else if(source == 1){
            Log.i(TAG, "Version 2");
            showNotificationP();

        }
        else{
            showNotificationP2();
            Log.i(TAG, "Version 3");
        }


        return super.onStartCommand(intent, flags, startId);
    }



    /**
     * Creates a notification and shows it in the OS drag-down status bar (timer zwischen 2 und 3)
     */
    private void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, ZehnTage.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(r.getString(R.string.notification_title))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle(r.getString(R.string.notification_title))
                .setContentText(r.getString(R.string.notification_text))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();



        // Send the notification to the system.
        mNM.notify(NOTIFICATION, notification);

        // Stop the service when we are finished
        stopSelf();
    }

    /**
     * Creates a notification and shows it in the OS drag-down status bar (Timer zwischen 3 und 4)
     */
    private void showNotificationP() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Level4Start.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(r.getString(R.string.notification_title))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle(r.getString(R.string.notification_title))
                .setContentText(r.getString(R.string.notification_text))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();



        // Send the notification to the system.
        mNM.notify(NOTIFICATION, notification);

        // Stop the service when we are finished
        stopSelf();
    }

    /**
     * Creates a notification and shows it in the OS drag-down status bar (Timer zwischen 4 und 5)
     */
    private void showNotificationP2() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Level5Start.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(r.getString(R.string.notification_title))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle(r.getString(R.string.notification_title))
                .setContentText(r.getString(R.string.notification_text))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();



        // Send the notification to the system.
        mNM.notify(NOTIFICATION, notification);

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
