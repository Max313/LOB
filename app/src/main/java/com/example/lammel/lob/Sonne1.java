package com.example.lammel.lob;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Sonne1 extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;
    private ImageButton sungrey;
    private ImageButton sunyellow;
    private ImageButton sun;
    private ImageButton glowgrey;
    private ImageButton glowcolor;
    private ImageButton glow;
    private Button ziel;
    private Button ressource;
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Buttons and more
    private ImageView record;
    private ImageView recordOn;
    private ImageView play;
    private ImageView pause;
    private Button weiter;
    private Button uebersicht;
    private Boolean tour;
    private Intent intent;
    private String fileName;
    private File file;
    private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne1);
        this.setTitle("LOB - Sonne der Erkenntnis 1/8");


        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.sonne1_xml, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_sonne1);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);


        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);
        forward.setVisibility(View.VISIBLE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.GONE);


        //Buttons and more on action
        fileName = "Sonne1";

        //Ask for Boolean
        tour = getIntent().getExtras().getBoolean("Tour");

        //Set Action - Click
        weiter = (Button) findViewById(R.id.Weiter1_Button);
        weiter.setOnClickListener(this);

        uebersicht = (Button) findViewById(R.id.zurUebersicht1_Button);
        uebersicht.setOnClickListener(this);

        play = (ImageView) findViewById(R.id.play1_Button);
        play.setOnClickListener(this);

        pause = (ImageView) findViewById(R.id.pause1_Button);
        pause.setOnClickListener(this);

        record = (ImageView) findViewById(R.id.microphone1_Button1);
        record.setOnClickListener(this);

        recordOn = (ImageView) findViewById(R.id.microphone1_Button2);
        recordOn.setOnClickListener(this);



       //Check if a tour is startet or not and set the necessary visibility of the buttons
        if(tour){
            uebersicht.setVisibility(View.GONE);
        }
        else {
            weiter.setVisibility(View.GONE);
        }

        /**file = new File("Sonne1");
        if(!file.exists()){
            file = new File(getFilesDir(), fileName);
            play.setEnabled(false);
        }
        else if(file.length() == 0){
            play.setEnabled(false);
        }*/
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.Sonne).setEnabled(false);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.ziel:
                startActivity(new Intent(this, Level1Zieldefinition.class));
                return true;

            case R.id.tabelle:
                startActivity(new Intent(this, UebersichtTable.class));
                return true;

            case R.id.Sonne:
                startActivity(new Intent(this, SonneDerErkenntnisStart.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Check if there is the needed permission to record_audio
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case 101:
                if(!(grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    record.setEnabled(false);
                    play.setEnabled(false);
                    break;
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions,grantResults);
                break;
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.Weiter1_Button:
               intent = new Intent(view.getContext(), Sonne2.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            case R.id.zurUebersicht1_Button:
                intent = new Intent(view.getContext(), Level4SonneDerErkenntnis.class);
                intent.putExtra("Source", 1);
                startActivity(intent);
                break;

            case R.id.microphone1_Button1:
                record.setVisibility(view.GONE);
                recordOn.setVisibility(view.VISIBLE);
                break;

            case R.id.microphone1_Button2:
                recordOn.setVisibility(view.GONE);
                record.setVisibility(view.VISIBLE);
                break;


            case R.id.play1_Button:
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
                break;

            case R.id.pause1_Button:
                pause.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
                break;

            case R.id.back_Button:
                startActivity(new Intent(this, Level4SonneDerErkenntnis.class));
                break;

            case R.id.forward_Button:
                intent = new Intent(view.getContext(), Sonne2.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            default:
                break;

        }
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
