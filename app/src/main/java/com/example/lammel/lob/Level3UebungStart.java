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

public class Level3UebungStart extends AppCompatActivity implements View.OnClickListener{

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
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Button
    private Button uebung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3_uebung_start);
        this.setTitle("LOB - Stärkeninseln");
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

        sungrey = (ImageButton) findViewById(R.id.sonneGrau_Button);
        sungrey.setVisibility(View.VISIBLE);

        sunyellow = (ImageButton) findViewById(R.id.sonneLeer_Button);
        sunyellow.setVisibility(View.GONE);

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);
        sun.setVisibility(View.GONE);

        eins = (TextView) findViewById(R.id.footer1_TextView);
        eins.setVisibility(View.GONE);

        zwei = (TextView) findViewById(R.id.footer2_TextView);
        zwei.setVisibility(View.GONE);

        drei = (TextView) findViewById(R.id.footer3_TextView);
        drei.setVisibility(View.VISIBLE);

        vier = (TextView) findViewById(R.id.footer4_TextView);
        vier.setVisibility(View.GONE);

        fuenf = (TextView) findViewById(R.id.footer5_TextView);
        fuenf.setVisibility(View.GONE);

        //Button action
        uebung = (Button) findViewById(R.id.button3);
        uebung.setOnClickListener(this);

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
        switch(view.getId()){
            case R.id.button3:
                startActivity(new Intent(this, Level4InselDesSehenden.class));
                break;

            case R.id.back_Button:
                startActivity(new Intent(this, UebersichtTable.class));
                break;

            case R.id.forward_Button:
                startActivity(new Intent(this, Level4InselDesSehenden.class));
                break;

            case R.id.ziel_Button:
                startActivity(new Intent(this, Level1Problemdefinition.class));
                break;

            case R.id.gluehbirneLeuchtend_Button:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

            default:
                break;
        }

    }
}
