package com.example.lammel.lob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by stina on 06.07.17.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {

    private long countdown;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Juhu", "komme rein");
    }

}
