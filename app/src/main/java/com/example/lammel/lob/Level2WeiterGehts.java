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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;

/**
 * Level2WeiterGehts ist der Übergang von Level 2 zu Level 3: Hier kann man noch einmal entscheiden ob man mit seinen Ideen zufrieden ist oder ob man doch noch mehr Lösungswege betrachten will
 * (Text und 2 Buttons)
 * Geht man weiter Richtung Level 3 werden die Hausaufgaben freigeschaltet, auf die man entweder mit dem Popup oder über das Menü zugreifen kann
 * Hat der Nutzer bereits bei ZehnTage gewartet oder war schon in Level 3 so wird die Pause übersprungen, ansonsten kommt eine Pause zwischen Level 2 und 3
 */

public class Level2WeiterGehts extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Buttons
    private Button button1, button2;
    private TextView txt;
    private int loesungsCounter;
    private AppCompatDelegate delegate;
    private int counter = 0;

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
        delegate.getSupportActionBar().setLogo(R.mipmap.wegweiser);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        loesungsCounter = getIntent().getExtras().getInt("LoesungsCounter");

        button1 = (Button) findViewById(R.id.weiterGehts_Button1);
        button2 = (Button) findViewById(R.id.weiterGehts_Button2);
        txt = (TextView) findViewById(R.id.weiterGehts_Textview);

        if(loesungsCounter == 0){
            txt.setText("So individuell jeder von uns ist, so individuell sind auch unsere Lösungswege. Neue Wege sind Übungen, die dir dabei helfen sollen den richtigen Lösungsansatz für dein Problem zu finden. Bist du sicher, dass du keine davon ausprobieren willst? ");
        }
        if(loesungsCounter >= 5){
            txt.setText("Du hast alle Lösungswege der App ausprobiert. Hoffentlich haben sie dir geholfen auf Ideen zu kommen, wie du der Lösung näher kommst. Gehe nun weiter zu Level 3 um mehr zu erfahren.");
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
        if (!saved.getBoolean("MenuHausaufgabe", false)){
            menu.findItem(R.id.Hausaufgabe).setEnabled(false);
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
        editor = saved.edit();
        final View view = v;
        switch (v.getId()){
            case R.id.weiterGehts_Button1:
                saved = getSharedPreferences(PREFS_NAME, 0);
                editor = saved.edit();

                if(saved.getInt("zielStatus", 0) < 2){
                    editor.putInt("zielStatus", 2);
                }

                else if(saved.getInt("ideeStatus",0) < 1){
                    editor.putInt("ideeStatus", 1);
                }

                editor.putInt("tabStatus", 2);
                editor.apply();

                if(counter == 0 && !saved.getBoolean("HausaufgabeSave", false)) {
                    editor.putBoolean("TagebuchSave", true);
                    editor.putBoolean("MünzeSave", true);
                    editor.putBoolean("WürfelSave", true);
                    editor.putBoolean("HausaufgabeSave", true);
                    editor.putBoolean("MenuHausaufgabe", true);
                    editor.apply();
                    builder.setTitle("Hausaufgabe");
                    builder.setMessage("Ab sofort kannst du auch Hausaufgaben machen.\nDiese sind freiwillig, aber können dir dabei helfen das Erlernte besser zu verstehen und zu üben. \nDu kannst sie in den Pausen zwischen den Leveln machen, wenn du Lust hast.");
                    //builder.setMessage("Am besten ist es, wenn du die Lösungswege, die du gefunden hast, auch ausprobierst. Eine kleine Hausaufgabe wird dich dabei unterstützen. Du hast jetzt den Zugriff auf alle drei Übungen freigeschaltet.");
                    builder.setPositiveButton(R.string.weiter_Button, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            startNext();
                        }
                    });
                    builder.setNeutralButton("Ansehen", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            startHausaufgaben(view);
                        }
                    });
                    AlertDialog dialogX = builder.create();
                    dialogX.show();
                }
                else{
                    if(saved.getBoolean("zehnTage", false) || saved.getBoolean("pause2", false)){
                        startActivity(new Intent(this, Level3Start.class));
                    }
                    else{
                        startActivity(new Intent(this, PauseZwischen2und3Start.class));
                    }
                    counter = 0;
                }

                break;

            case R.id.weiterGehts_Button2:
                if(loesungsCounter == 0){
                    startActivity(new Intent(this, Level2Veraenderung.class));
                    break;
                }
                else if(loesungsCounter == 1){
                    startActivity(new Intent(this, Level2Ausnahmen.class));
                    break;
                }
                else if(loesungsCounter == 2){
                    startActivity(new Intent(this, Level2HypoLoesung.class));
                    break;
                }
                else if(loesungsCounter == 3){
                    startActivity(new Intent(this, Level2Universalloesung.class));
                    break;
                }
                else if(loesungsCounter == 4) {
                    startActivity(new Intent(this, Level2Exitstrategie.class));
                    break;
                }
                else if(loesungsCounter == 5) {
                    startActivity(new Intent(this, Level2KeineLoesung.class));
                    break;
                }

            default:
                break;

        }
    }

    private void startHausaufgaben(View v) {
        Intent intent = new Intent(v.getContext(), MenuHausaufgabe.class);
        //intent.putExtra("Hausaufgabe", 3);
        startActivity(intent);
    }

    private void startNext() {

        saved = getSharedPreferences(PREFS_NAME, 0);

        if(saved.getBoolean("zehnTage", false) || saved.getBoolean("pause2", false)){
            startActivity(new Intent(this, Level3Start.class));
        }
        else{
            startActivity(new Intent(this, PauseZwischen2und3Start.class));
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
