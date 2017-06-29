package com.example.lammel.lob;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;

//Hier wird in Level 1 das Ziel zum ersten Mal festgelegt und gespeichert.
//Ab hier kann man im Menü auf Ziele zugreifen

public class Level1Zieldefinition extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Toolbar
    private AppCompatDelegate delegate;


    //EditText und Ziel
    private String ziel;
    private String defaultZiel = "Nimm dir etwas Zeit und Ruhe und halte hier dein Ziel so knapp und präzise fest wie möglich.\nDu kannst natürlich jederzeit darauf zurückgreifen und Änderungen anpassen";
    private EditText zieltxt;

    //Button Weiter
    private Button zielFesthalten_Button;

    //logging
    private final static String TAG = "Zieldefinition";
    private long start;
    private long end;

    //shared Preferences
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;


    //Hier wird das Ziel festgelegt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_zieldefinition);
        this.setTitle("Dein Ziel");



        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level1_zieldefinition, fragment);
        transaction.commit();

        //Set Status - Footer
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();

        editor.putInt("tabStatus", 1);
        editor.apply();

        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level1_zieldefinition);

        //Add the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level1));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.berg);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);


        //logging
        start = System.currentTimeMillis();
        Log.i(TAG,"Start: "+start);


        //Buttons
        zielFesthalten_Button = (Button) findViewById(R.id.zielFesthalten_Button);
        zielFesthalten_Button.setEnabled(false);
        zielFesthalten_Button.setOnClickListener(this);

        //Edit Text + sharedPreference
        saved = getSharedPreferences(PREFS_NAME, 0);
        ziel = saved.getString("ZielSave", defaultZiel);
        zieltxt = (EditText) findViewById(R.id.zieldefinition_EditText);
        zieltxt.setHorizontallyScrolling(false);
        zieltxt.setLines(6);
        zieltxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

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
        if (!saved.getBoolean("MenuHausaufgabe", false)){
            menu.findItem(R.id.Hausaufgabe).setEnabled(false);
        }
        menu.findItem(R.id.action_help).setVisible(true);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.action_help).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    //Menüaktivität
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start_menu:
                startActivity(new Intent(this, LevelIntro.class));
                return true;

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

            case R.id.Impressum:
                startActivity(new Intent(this, MenuImpressum.class));
                return true;

            case R.id.action_delete:
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().clear().commit();
                deleteFiles();
                startNew();
                return true;

            case R.id.action_help:
                AlertDialog.Builder builder = new AlertDialog.Builder(Level1Zieldefinition.this);
                builder.setTitle("Ziel - Hilfe");
                builder.setMessage("Dein Ziel sollte einige Kriterien erfüllen:\n" +
                        "\u2022 Betrachte es als Beginn von etwas Neuem\n" +
                        "\u2022 Es ist umsetzbar und erreichbar\n" +
                        "\u2022 Du willst es aus eigener Motivation heraus erreichen\n" +
                        "\u2022 Wenn du es erreicht hast, ist eine Besserung eingetreten\n" +
                        "\u2022 Du fühlst etwas positives, wenn du an das Ziel denkst");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogX = builder.create();
                dialogX.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteFiles(){
        File file1 = new File(this.getFilesDir() +"/" + "sonne1" +".3gp");
        if(file1.exists()){
            file1.delete();
        }

        File file2 = new File(this.getFilesDir() + "/"+ "sonne2" + ".3gp");
        if(file2.exists()){
            file2.delete();
        }

        File file3 = new File(this.getFilesDir() + "/"+ "sonne3" + ".3gp");
        if(file3.exists()){
            file3.delete();
        }

        File file4 = new File(this.getFilesDir() + "/"+ "sonne4" + ".3gp");
        if(file4.exists()){
            file4.delete();
        }

        File file5 = new File(this.getFilesDir() + "/"+ "sonne5" + ".3gp");
        if(file5.exists()){
            file5.delete();
        }

        File file6 = new File(this.getFilesDir() + "/"+ "sonne6" + ".3gp");
        if(file6.exists()){
            file6.delete();
        }


        File file7 = new File(this.getFilesDir() + "/"+ "sonne7" + ".3gp");
        if(file7.exists()){
            file7.delete();
        }


        File file8 = new File(this.getFilesDir() + "/"+ "sonne8" + ".3gp");
        if(file8.exists()){
            file8.delete();
        }
    }

    public void startNew(){
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zielFesthalten_Button:
                //logging
                end = System.currentTimeMillis();
                Log.i(TAG,"Duration: "+(end - start));

                //Weiter und Ziel abspeichern
                saved = getSharedPreferences(PREFS_NAME, 0);
                editor = saved.edit();
                editor.putString("ZielSave", ziel);
                editor.putBoolean("MenuZiel", true);
                editor.apply();

                new AlarmTask(this).cancelAlarm();

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