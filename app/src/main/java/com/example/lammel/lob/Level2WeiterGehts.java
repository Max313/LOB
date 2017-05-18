package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class Level2WeiterGehts extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Buttons
    private Button button1, button2;
    private int loesungsCounter;
    private AppCompatDelegate delegate;
    private Boolean tagebuch, muenze, wuerfel;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_weiter_gehts);
        this.setTitle("Lösungswege");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level2_weiter_gehts, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level2_weiter_gehts);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level2));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.wegweiserb);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        loesungsCounter = getIntent().getExtras().getInt("LoesungsCounter");

        button1 = (Button) findViewById(R.id.weiterGehts_Button1);
        button2 = (Button) findViewById(R.id.weiterGehts_Button2);


        if(loesungsCounter == 5){
            button2.setEnabled(false);
        }

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);


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
                startActivity(new Intent(this, SonneDerErkenntnisStart.class));
                return true;

            case R.id.Hausaufgabe:
                startActivity(new Intent(this, MenuHausaufgabe.class));
                return true;

            case R.id.action_delete:
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().clear().commit();
                deleteFiles();
                startNew();
                return true;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(Level2WeiterGehts.this);
        saved = getSharedPreferences(PREFS_NAME, 0);
        tagebuch = saved.getBoolean("TagebuchSave", false);
        wuerfel = saved.getBoolean("WürfelSave", false);
        muenze = saved.getBoolean("MünzeSave", false);
        editor = saved.edit();
        switch (v.getId()){
            case R.id.weiterGehts_Button1:
                if((tagebuch && wuerfel && muenze) != true){
                    editor.putBoolean("TagebuchSave", true);
                    editor.putBoolean("WürfelSave", true);
                    editor.putBoolean("MünzeSave", true);
                    editor.apply();
                    builder.setTitle("Hausaufgabe");
                    builder.setMessage("Es wurden alle Hausaufgaben für dich freigeschaltet. Du kannst zu jeder Zeit darauf zugreifen und eine davon ausprobieren, wenn du willst. ");
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            startNext();
                        }
                    });
                    builder.setNeutralButton("Ansehen",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            startHausaufgaben();
                        }
                    });
                    AlertDialog dialogY = builder.create();
                    dialogY.show();
                }
                else {

                    //Set Status - Footer
                    saved = getSharedPreferences(PREFS_NAME, 0);
                    editor = saved.edit();

                    if(saved.getInt("ideeStatus",0) < 2){
                        editor.putInt("ideeStatus", 2);
                    }
                    else if(saved.getInt("ressourceStatus", 0) < 1){
                        editor.putInt("ressourceStatus", 1);
                    }

                    editor.putInt("tabStatus", 3);
                    editor.apply();

                    startActivity(new Intent(this, Level3Start.class));
                }
                break;

            case R.id.weiterGehts_Button2:
                if(loesungsCounter == 0){
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 0);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2Ausnahmen.class));
                    break;
                }
                else if(loesungsCounter == 1){
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 1);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2HypoLoesung.class));
                    break;
                }
                else if(loesungsCounter == 2){
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 2);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2Universalloesung.class));
                    break;
                }
                else if(loesungsCounter == 3) {
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 3);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2Exitstrategie.class));
                    break;
                }
                else if(loesungsCounter == 4) {
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 4);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2KeineLoesung.class));
                    break;
                }

            default:
                break;

        }
    }

    private void startHausaufgaben() {
        startActivity(new Intent(this, MenuHausaufgabe.class));
    }

    private void startNext() {
        startActivity(new Intent(this, Level3Start.class));
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
