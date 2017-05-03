package com.example.lammel.lob;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Sonne1 extends AppCompatActivity implements View.OnClickListener{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne1);
        this.setTitle("LOB - Sonne der Erkenntnis 1/8");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);


        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);
        forward.setVisibility(View.VISIBLE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.GONE);

        ziel = (Button) findViewById(R.id.ziel_Button);
        ziel.setOnClickListener(this);

        glowgrey = (ImageButton) findViewById(R.id.gluehbirneDurchsichtig_Button);
        glowgrey.setVisibility(View.GONE);

        glowcolor = (ImageButton) findViewById(R.id.gluehbirneDunkel_Button);
        glowcolor.setVisibility(View.GONE);

        glow = (ImageButton) findViewById(R.id.gluehbirneLeuchtend_Button);
        glow.setVisibility(View.VISIBLE);
        glow.setOnClickListener(this);

        ressource = (Button) findViewById(R.id.ressourcen_Button);
        ressource.setOnClickListener(this);

        sungrey = (ImageButton) findViewById(R.id.sonneGrau_Button);
        sungrey.setVisibility(View.GONE);

        sunyellow = (ImageButton) findViewById(R.id.sonneLeer_Button);
        sunyellow.setVisibility(View.VISIBLE);

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);
        sun.setVisibility(View.GONE);

        eins = (TextView) findViewById(R.id.footer1_TextView);
        eins.setVisibility(View.GONE);

        zwei = (TextView) findViewById(R.id.footer2_TextView);
        zwei.setVisibility(View.GONE);

        drei = (TextView) findViewById(R.id.footer3_TextView);
        drei.setVisibility(View.GONE);

        vier = (TextView) findViewById(R.id.footer4_TextView);
        vier.setVisibility(View.VISIBLE);

        fuenf = (TextView) findViewById(R.id.footer5_TextView);
        fuenf.setVisibility(View.GONE);

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
                intent.putExtra("Tour", false);
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

            case R.id.ziel_Button:
                startActivity(new Intent(this, Level1Problemdefinition.class));
                break;

            case R.id.gluehbirneLeuchtend_Button:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

            case R.id.ressourcen_Button:
                Intent intent = new Intent(view.getContext(), Staerkeinsel.class);
                intent.putExtra("LoesungsCounter", 0);
                startActivity(intent);
                break;

            default:
                break;

        }
    }


}
