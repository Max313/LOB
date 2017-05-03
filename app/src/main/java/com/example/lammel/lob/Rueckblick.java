package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class Rueckblick extends AppCompatActivity implements View.OnClickListener{


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
    private SeekBar seekBar;
    private Button weiter;
    private TextView txt;
    private int fortschritt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rueckblick);

        this.setTitle("LOB - Rückblick");
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
        sunyellow.setVisibility(View.GONE);

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);
        sun.setVisibility(View.VISIBLE);
        sun.setOnClickListener(this);

        eins = (TextView) findViewById(R.id.footer1_TextView);
        eins.setVisibility(View.GONE);

        zwei = (TextView) findViewById(R.id.footer2_TextView);
        zwei.setVisibility(View.GONE);

        drei = (TextView) findViewById(R.id.footer3_TextView);
        drei.setVisibility(View.GONE);

        vier = (TextView) findViewById(R.id.footer4_TextView);
        vier.setVisibility(View.GONE);

        fuenf = (TextView) findViewById(R.id.footer5_TextView);
        fuenf.setVisibility(View.VISIBLE);

        //Buttons and more in action
        txt = (TextView) findViewById(R.id.Seek1_TextView);

        seekBar = (SeekBar) findViewById(R.id.rueckblick_seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                fortschritt = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txt.setText("" + fortschritt);

            }
        });

        weiter = (Button) findViewById(R.id.rWeiter_Button);
        weiter.setOnClickListener(this);

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
                startActivity(new Intent(this, Level4SonneDerErkenntnis.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.rWeiter_Button:
                if(fortschritt >= 8){
                    startActivity(new Intent(this, Ende.class));
                    break;
                }

                else {
                    startActivity(new Intent(this, Neuorientierung.class));
                    break;
                }
            case R.id.back_Button:
                startActivity(new Intent(this, Sonne8.class));
                break;

            case R.id.forward_Button:
                if(fortschritt >= 8){
                    startActivity(new Intent(this, Ende.class));
                    break;
                }

                else {
                    startActivity(new Intent(this, Neuorientierung.class));
                    break;
                }

            case R.id.ziel_Button:
                startActivity(new Intent(this, Level1Problemdefinition.class));
                break;

            case R.id.gluehbirneLeuchtend_Button:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

            case R.id.ressourcen_Button:
                startActivity(new Intent(this, Staerkeinsel.class));
                break;

            case R.id.sonneLeuchtend_Button:
                startActivity(new Intent(this, Level4InselDesSehenden.class));
                break;
        }



    }
}
