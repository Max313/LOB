package com.example.lammel.lob;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Timer extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    private AppCompatDelegate delegate;

    //Timer
    private CountDownTimer countdown;
    private TextView days;
    private int d;
    private TextView hours;
    private int h;
    private TextView minutes;
    private int m;
    private TextView seconds;
    private int s;
    private Button weiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        this.setTitle("Pause");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.timer, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_timer);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTableHeaderText));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.pauseicon);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        weiter = (Button) findViewById(R.id.Timer1Weiter_Button);
        weiter.setOnClickListener(this);
        weiter.setVisibility(View.GONE);

        //Timer der 10 Tage runterläuft 864000000 ms
        //Timer der 1 Min runterläuft 60000 ms

        days = (TextView) findViewById(R.id.dAnzeige_TextView);
        hours = (TextView) findViewById(R.id.hAnzeige_TextView);
        minutes = (TextView) findViewById(R.id.mAnzeige_TextView);
        seconds = (TextView) findViewById(R.id.sAnzeige_TextView);


        countdown = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                d = (int) millisUntilFinished / 86400000;
                h = (int) ((millisUntilFinished - (d * 86400000))/3600000);
                m = (int) ((millisUntilFinished - ((d * 86400000)+ (h*3600000)))/60000);
                s = (int) ((millisUntilFinished - ((d * 86400000)+ (h*3600000)+ (m*60000)))/1000);

                days.setText(String.format("%02d",d));
                hours.setText(String.format("%02d",h));
                minutes.setText(String.format("%02d",m));
                seconds.setText(String.format("%02d",s));

            }

            public void onFinish() {
                weiter.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, Level4Start.class));
    }

    public void startNew(){
        startActivity(new Intent(this, MainActivity.class));
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
