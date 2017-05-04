package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MenuZiel extends AppCompatActivity implements View.OnClickListener{

    private Button speicherButton, zurueckButton;
    private String ziel;

    //shared Preferences
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;


    //Hier ist man im Menübereich "Ziel" wo man jederzeit sein Ziel ansehen und ändern kann, wenn man es bereits festgelegt hat in Level1Zieldefinition
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ziel);
        this.setTitle("LOB - Dein Ziel");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        zurueckButton = (Button) findViewById(R.id.menuZiel_zurueckButton);
        zurueckButton.setOnClickListener(this);
        speicherButton = (Button) findViewById(R.id.menuZiel_zielButton);
        speicherButton.setEnabled(false);
        speicherButton.setOnClickListener(this);

        //EditText und sharedPreferences
        saved = getSharedPreferences(PREFS_NAME, 0);
        ziel = saved.getString("ZielSave", "Hier kannst du dein Ziel eintragen.");
        final EditText txt = (EditText) findViewById(R.id.menuZiel_EditText);
        txt.setHint(ziel);
        txt.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt.length() == 0)
                    speicherButton.setEnabled(false); //disable button if no text entered
                else
                    ziel = txt.getText().toString();
                    speicherButton.setEnabled(true);  //otherwise enabled

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            //Ziel neu abspeichern und zurück
            case R.id.menuZiel_zielButton:
                saved = getSharedPreferences(PREFS_NAME, 0);
                editor = saved.edit();
                editor.putString("ZielSave", ziel);
                editor.apply();
                onBackPressed();
                break;

            case R.id.menuZiel_zurueckButton:
                onBackPressed();
                break;

            default:
                break;
        }
    }

}
