package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Level1ZielVerwahren extends AppCompatActivity implements View.OnClickListener {

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
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Button
    Button zielVerwahren_Button;
    public static final String PREFS_NAME = "LOBPrefFile";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_zielverwahren);
        this.setTitle("LOB - Dein Ziel");
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

        glowgrey = (ImageButton) findViewById(R.id.gluehbirneDurchsichtig_Button);
        glowgrey.setVisibility(View.VISIBLE);

        glowcolor = (ImageButton) findViewById(R.id.gluehbirneDunkel_Button);
        glowcolor.setVisibility(View.GONE);

        glow = (ImageButton) findViewById(R.id.gluehbirneLeuchtend_Button);
        glow.setVisibility(View.GONE);

        sungrey = (ImageButton) findViewById(R.id.sonneGrau_Button);
        sungrey.setVisibility(View.VISIBLE);

        sunyellow = (ImageButton) findViewById(R.id.sonneLeer_Button);
        sunyellow.setVisibility(View.GONE);

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);
        sun.setVisibility(View.GONE);

        eins = (TextView) findViewById(R.id.footer1_TextView);
        eins.setVisibility(View.VISIBLE);

        zwei = (TextView) findViewById(R.id.footer2_TextView);
        zwei.setVisibility(View.GONE);

        drei = (TextView) findViewById(R.id.footer3_TextView);
        drei.setVisibility(View.GONE);

        vier = (TextView) findViewById(R.id.footer4_TextView);
        vier.setVisibility(View.GONE);

        fuenf = (TextView) findViewById(R.id.footer5_TextView);
        fuenf.setVisibility(View.GONE);

        //Button
        zielVerwahren_Button = (Button) findViewById(R.id.zielVerwahren_Button);
        zielVerwahren_Button.setOnClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.tabelle).setEnabled(false);
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
                        startActivity(new Intent(this, MenuZiel.class));
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //startActivity level 2
            case R.id.zielVerwahren_Button:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

            case R.id.back_Button:
                startActivity(new Intent(this, Level1Zieldefinition.class));
                break;

            case R.id.forward_Button:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

                default:
                    break;


        }
    }

    protected void onStop(){
        //Beim Stoppen wird das Ziel abgespeichert damit man beim erneute öffnen darauf zugreifen kann
        super.onStop();
        String ziel = Level1Zieldefinition.getZiel();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("ZielString", ziel);

        // Commit the edits!
        editor.commit();

    }

}
