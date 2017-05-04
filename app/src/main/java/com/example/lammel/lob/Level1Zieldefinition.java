package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

//Hier wird in Level 1 das Ziel zum ersten Mal festgelegt und gespeichert.
//Ab hier kann man im Menü auf Ziele zugreifen

public class Level1Zieldefinition extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

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

    private AppCompatDelegate delegate;

    //EditText und Ziel
    private String ziel;
    private String defaultZiel = "Nimm dir etwas Zeit und Ruhe und halte hier dein Ziel so knapp und präzise fest wie möglich.\nDu kannst natürlich jederzeit darauf zurückgreifen und Änderungen anpassen";
    private EditText zieltxt;

    //Button Weiter
    private Button zielFesthalten_Button;

    //shared Preferences
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    SharedPreferences.Editor editor;


    //Hier wird das Ziel festgelegt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_zieldefinition);
        this.setTitle("LOB - Dein Ziel");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level1_zieldefinition, fragment);
        transaction.commit();

        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level1_zieldefinition);

        //Add the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        //Footer_Fragment Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);
        forward.setVisibility(View.VISIBLE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.GONE);

        //Buttons
        zielFesthalten_Button = (Button) findViewById(R.id.zielFesthalten_Button);
        zielFesthalten_Button.setEnabled(false);
        zielFesthalten_Button.setOnClickListener(this);

        //Edit Text + sharedPreference
        saved = getSharedPreferences(PREFS_NAME, 0);
        ziel = saved.getString("ZielSave", defaultZiel);
        zieltxt = (EditText) findViewById(R.id.zieldefinition_EditText);
        if (ziel != defaultZiel) {
            zieltxt.setText(ziel);
            zielFesthalten_Button.setEnabled(true);
        }
        zieltxt.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (zieltxt.length() == 0) {
                    zielFesthalten_Button.setEnabled(false); //disable button if no text entered//
                } else {
                    ziel = zieltxt.getText().toString();
                    zielFesthalten_Button.setEnabled(true);  //otherwise enabled
                }
                if (zieltxt.length() == 0)
                    zielFesthalten_Button.setEnabled(false); //disable button if no text entered
                else
                    ziel = zieltxt.getText().toString();
                zielFesthalten_Button.setEnabled(true);  //otherwise enabled

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    //Welche Menüoptionen sind enabled
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        saved = getSharedPreferences(PREFS_NAME, 0);

        if (!saved.getBoolean("MenuZiel", false)) {
            menu.findItem(R.id.ziel).setEnabled(false);
        }
        if (!saved.getBoolean("MenuTabelle", false)) {
            menu.findItem(R.id.tabelle).setEnabled(false);
        }
        if (!saved.getBoolean("MenuSonne", false)) {
            menu.findItem(R.id.Sonne).setEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Menüaktivität
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
        switch (v.getId()) {
            case R.id.zielFesthalten_Button:
                //Weiter und Ziel abspeichern
                saved = getSharedPreferences(PREFS_NAME, 0);
                editor = saved.edit();
                editor.putString("ZielSave", ziel);
                editor.putBoolean("MenuZiel", true);
                editor.apply();
                startActivity(new Intent(this, Level1ZielVerwahren.class));
                break;

            case R.id.back_Button:
                startActivity(new Intent(this, Level1Problemdefinition.class));
                break;

            case R.id.forward_Button:
                startActivity(new Intent(this, Level1ZielVerwahren.class));
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