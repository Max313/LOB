package com.example.lammel.lob;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BroadcastService extends Service {

    private final static String TAG = "BroadcastService";

    public static final String COUNTDOWN_BR = "com.example.lammel.lob.countdown_br";
    Intent bi = new Intent(COUNTDOWN_BR);

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private long startValue;
    private static final long countdown = 60000;

    CountDownTimer cdt;


   @Override
    public void onCreate() {
        super.onCreate();
       cdt = null;
       Log.i(TAG, "on create");
    }

    @Override
    public void onDestroy(){
        cdt.cancel();
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i(TAG, "Starting timer...");


            saved = getSharedPreferences(PREFS_NAME, 0);
            if(saved.getLong("pauseTime", (long) 0) != (long) 0){
                long savedTime = saved.getLong("pauseTime", (long) 0);
                long currentTime = System.currentTimeMillis();

                long difference = currentTime - savedTime;
                startValue = countdown - difference;
            }
            else{
                startValue = countdown;
            }
        cdt = new CountDownTimer(startValue, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                bi.putExtra("countdown", millisUntilFinished);
                sendBroadcast(bi);

            }

            @Override
            public void onFinish() {
                bi.putExtra("countdown",(long) 0);
                sendBroadcast(bi);
                Log.i(TAG, "Timer finished");

            }
        };
        cdt.start();
        if(!saved.getBoolean("alarmStart", false)) {
            new AlarmTask(this).run();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
