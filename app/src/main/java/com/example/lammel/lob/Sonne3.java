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
import android.widget.TextView;

public class Sonne3 extends AppCompatActivity implements View.OnClickListener{


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
    private Button weiter;
    private Button uebersicht;
    private Boolean tour;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne3);

        this.setTitle("LOB - Sonne der Erkenntnis 3/8");
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
        tour = getIntent().getExtras().getBoolean("Tour");

        weiter = (Button) findViewById(R.id.Weiter3_Button);
        weiter.setOnClickListener(this);

        uebersicht = (Button) findViewById(R.id.zurUebersicht3_Button);
        uebersicht.setOnClickListener(this);

        if(tour){
            uebersicht.setVisibility(View.GONE);
        }

        else{
            weiter.setVisibility(View.GONE);
        }
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Weiter3_Button:
                intent = new Intent(view.getContext(), Sonne4.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            case R.id.zurUebersicht3_Button:
                intent = new Intent(view.getContext(), Level4SonneDerErkenntnis.class);
                intent.putExtra("Source", 3);
                startActivity(intent);
                break;

            case R.id.back_Button:
                if(tour){
                    intent = new Intent(view.getContext(), Sonne2.class);
                    intent.putExtra("Tour", true);
                    startActivity(intent);
                    break;
                }
                else{
                    intent = new Intent(view.getContext(), Level4SonneDerErkenntnis.class);
                    intent.putExtra("Source", 3);
                    startActivity(intent);
                    break;
                }

            case R.id.forward_Button:
                if(tour){
                    intent = new Intent(view.getContext(), Sonne4.class);
                    intent.putExtra("Tour", true);
                    startActivity(intent);
                    break;
                }
                else{
                    intent = new Intent(view.getContext(), Level4SonneDerErkenntnis.class);
                    intent.putExtra("Source", 3);
                    startActivity(intent);
                    break;
                }

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
