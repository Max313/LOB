package com.example.lammel.lob;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Level2ZieldefinitionNeu extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;

    //Buttons and more
    private Button zieldefinition_zielButton, zurueckButton;
    private int wegCounter;

    //String fürs Ziel
    private String ziel;
    private String defaultZiel = "Hat sich an deinem Ziel inzwischen etwas verändert?\\n Woran wirst du merken dass dein Problem gelöst ist?\\nWas wäre in eurer Beziehung und deinem Verhalten anders als vorher?";
    private AppCompatDelegate delegate;

    //shared Preference
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;


    //In Level2 soll man immer wieder mit seinem Ziel konfrontiert werden, deswegen besteht hier zusätzlich zum Menü die Option das Ziel zu ändern
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_zieldefinition_neu);
        this.setTitle("LOB - Dein Ziel");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level2_zieldefinition_neu, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level2_zieldefinition_neu);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setVisibility(View.GONE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.VISIBLE);


        //Button and more in action
        wegCounter = getIntent().getExtras().getInt("WegCounter");
        zurueckButton = (Button) findViewById(R.id.zieldefinitionNeu_zurueckButton);
        zurueckButton.setOnClickListener(this);
        zieldefinition_zielButton = (Button) findViewById(R.id.zieldefinitionNeu_zielButton);
        zieldefinition_zielButton.setEnabled(false);
        zieldefinition_zielButton.setOnClickListener(this);

        //EditText Feld und shared Preferences
        saved = getSharedPreferences(PREFS_NAME, 0);
        ziel = saved.getString("ZielSave", defaultZiel);
        final EditText txt = (EditText) findViewById(R.id.zieldefinitionNeu_EditText);
        txt.setHint(ziel);
        txt.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt.length() == 0)
                    zieldefinition_zielButton.setEnabled(false); //disable button if no text entered
                else
                    ziel = txt.getText().toString();
                    zieldefinition_zielButton.setEnabled(true);  //otherwise enabled

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

            case R.id.action_delete:
                editor.clear();
                editor.apply();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Ziel neu abspeichern und Weiter
            case R.id.zieldefinitionNeu_zielButton:
                saved = getSharedPreferences(PREFS_NAME, 0);
                editor = saved.edit();
                editor.putString("ZielSave", ziel);
                editor.apply();

                Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                intent.putExtra("WegCounter", wegCounter);
                startActivity(intent);
                break;

            case R.id.zieldefinitionNeu_zurueckButton:
                Intent intent2 = new Intent(v.getContext(), Level2NeuerWeg.class);
                intent2.putExtra("WegCounter", wegCounter);
                startActivity(intent2);
                break;

            case R.id.back_Button:
                Intent intent3 = new Intent(v.getContext(), Level2NeuerWeg.class);
                intent3.putExtra("WegCounter", wegCounter);
                startActivity(intent3);
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
