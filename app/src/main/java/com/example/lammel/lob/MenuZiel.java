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
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ziel);
        this.setTitle("LOB - Dein Ziel");

        settings = getSharedPreferences(PREFS_NAME, 0);
        ziel = settings.getString("ZielString", "default");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        //ziel = Level1Zieldefinition.getZiel();
        zurueckButton = (Button) findViewById(R.id.menuZiel_zurueckButton);
        zurueckButton.setOnClickListener(this);
        speicherButton = (Button) findViewById(R.id.menuZiel_zielButton);
        speicherButton.setEnabled(false);
        speicherButton.setOnClickListener(this);
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
                startActivity(new Intent(this, Level1Zieldefinition.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.menuZiel_zielButton:
                Level1Zieldefinition.setZiel(ziel);
                onBackPressed();
                break;

            case R.id.menuZiel_zurueckButton:
                onBackPressed();
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
        editor.putBoolean("ZielAktiv", true);

        // Commit the edits!
        editor.commit();

    }
}
