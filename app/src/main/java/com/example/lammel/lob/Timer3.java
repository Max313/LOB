package com.example.lammel.lob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

/**
 * Hier läuft der Countdown der Pause zwischen 4 und 5 herunter: am Ende erscheint eine Notification und Level 5 kann gestartet werden
 */

public class Timer3 extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    private AppCompatDelegate delegate;


    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    //Timer
    private final static String TAG = "BroadcastReceiver";

    //Timer
    private long countdown;
    private TextView days;
    private int d;
    private TextView hours;
    private int h;
    private TextView minutes;
    private int m;
    private TextView seconds;
    private int s;
    private Button weiter;

    private long currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer3);
        this.setTitle("Pause");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.timer3, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_timer3);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTableHeaderText));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.mipmap.pauseicon);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        weiter = (Button) findViewById(R.id.Timer3Weiter_Button);
        weiter.setVisibility(View.GONE);


        //Timer der 10 Tage runterläuft 864000000 ms
        //Timer der 1 Min runterläuft 60000 ms
        saved = getSharedPreferences(PREFS_NAME, 0);

        countdown = saved.getLong("CountdownSave", 30000);

        editor = saved.edit();

        //Set the sourceId for the right AlarmTask
        editor.putInt("id", 3);
        editor.putBoolean("alarm4Start", false);
        editor.apply();

        if(saved.getLong("pauseTime", (long) 0) == (long) 0) {
            currentTime = System.currentTimeMillis();
            editor.putLong("pauseTime", currentTime);
            editor.apply();
        }

        days = (TextView) findViewById(R.id.d3Anzeige_TextView);
        hours = (TextView) findViewById(R.id.h3Anzeige_TextView);
        minutes = (TextView) findViewById(R.id.m3Anzeige_TextView);
        seconds = (TextView) findViewById(R.id.s3Anzeige_TextView);

        //Timer using a Service
        Intent intent_service = new Intent(getApplicationContext(), BroadcastService.class);
        startService(intent_service);
        Log.i(TAG, "Started service");
    }

    //Timer
    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGui(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i(TAG, "Registered broadcast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i(TAG, "Unregistered broadcast receiver");
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, BroadcastService.class));
        Log.i(TAG, "Stopped service");
        super.onDestroy();
    }

    private void updateGui(Intent intent){
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        if(intent.getExtras() != null) {
            long millisUntilFinished = intent.getLongExtra("countdown", countdown);

            editor.putBoolean("alarmStart", true);
            editor.apply();
            if (millisUntilFinished > 0) {
                d = (int) millisUntilFinished / 86400000;
                h = (int) ((millisUntilFinished - (d * 86400000)) / 3600000);
                m = (int) ((millisUntilFinished - ((d * 86400000) + (h * 3600000))) / 60000);
                s = (int) ((millisUntilFinished - ((d * 86400000) + (h * 3600000) + (m * 60000))) / 1000);

                days.setText(String.format("%02d", d));
                hours.setText(String.format("%02d", h));
                minutes.setText(String.format("%02d", m));
                seconds.setText(String.format("%02d", s));
            }


            else{
                Log.i(TAG, "Timer abgelaufen");
                days.setText("00");
                hours.setText("00");
                minutes.setText("00");
                seconds.setText("00");

                weiter.setVisibility(View.VISIBLE);
                weiter.setOnClickListener(this);

                //damit man die Pause nur einmal machen muss
                saved = getSharedPreferences(PREFS_NAME, 0);
                editor = saved.edit();

                editor.putBoolean("pause3", true);
                editor.putLong("pauseTime", (long) 0);
                editor.apply();

            }

        }

    }

    //Welche Menüoptionen sind enabled
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        saved = getSharedPreferences(PREFS_NAME, 0);

        if (!saved.getBoolean("MenuZiel", false)){
            menu.findItem(R.id.ziel).setEnabled(false);
        }
        if (!saved.getBoolean("MenuTabelle", false)){
            menu.findItem(R.id.tabelle).setEnabled(false);
        }
        if (!saved.getBoolean("MenuSonne", false)) {
            menu.findItem(R.id.Sonne).setEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    //Menüaktivität
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.start_menu:
                startActivity(new Intent(this, LevelIntro.class));
                return true;

            case R.id.ziel:
                startActivity(new Intent(this, MenuZiel.class));
                return true;

            case R.id.tabelle:
                startActivity(new Intent(this, UebersichtTable.class));
                return true;

            case R.id.Sonne:
                startActivity(new Intent(this, Level4SonneDerErkenntnis.class));
                return true;

            case R.id.Hausaufgabe:
                startActivity(new Intent(this, MenuHausaufgabe.class));
                return true;

            case R.id.Impressum:
                startActivity(new Intent(this, MenuImpressum.class));
                return true;

            case R.id.action_delete:
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().clear().commit();
                deleteFiles();
                startNew();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteFiles(){
        File file1 = new File(this.getFilesDir() +"/" + "sonne1" +".3gp");
        if(file1.exists()){
            file1.delete();
        }

        File file2 = new File(this.getFilesDir() + "/"+ "sonne2" + ".3gp");
        if(file2.exists()){
            file2.delete();
        }

        File file3 = new File(this.getFilesDir() + "/"+ "sonne3" + ".3gp");
        if(file3.exists()){
            file3.delete();
        }

        File file4 = new File(this.getFilesDir() + "/"+ "sonne4" + ".3gp");
        if(file4.exists()){
            file4.delete();
        }

        File file5 = new File(this.getFilesDir() + "/"+ "sonne5" + ".3gp");
        if(file5.exists()){
            file5.delete();
        }

        File file6 = new File(this.getFilesDir() + "/"+ "sonne6" + ".3gp");
        if(file6.exists()){
            file6.delete();
        }


        File file7 = new File(this.getFilesDir() + "/"+ "sonne7" + ".3gp");
        if(file7.exists()){
            file7.delete();
        }


        File file8 = new File(this.getFilesDir() + "/"+ "sonne8" + ".3gp");
        if(file8.exists()){
            file8.delete();
        }
    }

    public void startNew(){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View view) {
        //Set Status - Footer
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();

        if (saved.getInt("ideeStatus", 0) < 2) {
            editor.putInt("ideeStatus", 2);
        } else if (saved.getInt("ressourceStatus", 0) < 1) {
            editor.putInt("ressourceStatus", 1);
        }
        editor.putInt("tabStatus", 3);
        editor.apply();

        startActivity(new Intent(this, Level5Start.class));
    }


    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

}
