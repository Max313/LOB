package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import java.io.File;
import java.util.Random;

/**
 * Diese Hausaufgabe beschäftigt sich mit Level2Universalloesung
 * Es wird ein Würfel gewürfelt und je nach Zahl soll man an einem bestimmten Tag eine Aufgabe machen
 */

public class HausaufgabeWuerfel extends FragmentActivity implements View.OnClickListener, AppCompatCallback {



    //Buttons & more
    private Button zuAufgabe, zurueck, wuerfeln;
    private AppCompatDelegate delegate;
    private TextView txt;
    private int augenzahl;
    private int titelBild;
    private String wochentag, titelZahl;

    //shared Preferences
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hausaufgabe_wuerfel);
        this.setTitle("Hausaufgaben");


        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.hausaufgabe_wuerfel, fragment);
        transaction.commit();

        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_hausaufgabe_wuerfel);

        //Add the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);


        //Buttons und Funktion
        saved = getSharedPreferences(PREFS_NAME, 0);

        wuerfeln = (Button) findViewById(R.id.wuerfeln_button);
        wuerfeln.setOnClickListener(this);

        zuAufgabe = (Button) findViewById(R.id.wuerfel_Button2);
        zuAufgabe.setOnClickListener(this);

        zurueck = (Button) findViewById(R.id.wuerfel_Button1);
        zurueck.setOnClickListener(this);

        //Text -> Übung wurde schon gemacht oder noch nicht?
        saved = getSharedPreferences(PREFS_NAME, 0);

        txt = (TextView) findViewById(R.id.wuerfel_text);
        if(saved.getBoolean("WürfelSave", false)){
            txt.setText("Wie mit allem: Übung macht den Meister! Daher ein kleines Spiel dazu:\nBei Lösungsweg 4 hast du dir neue, \"verrückte\" Verhaltensweisen überlegt. Klicke auf \"Würfeln\" um einen die Übung zu starten.\nDie Augenzahl, die du würfelst, steht für einen Tag. 1 für Montag, 2 für Dienstag, 3 für Mittwoch, usw. Würfle und schaue, welcher Tag herauskommt.\nAn diesem Tag probierst du so ein \"verrücktes\" Verhalten aus. Beobachte ganz genau was an diesem Tag anders ist als am Rest der Woche.\nWenn du dich nicht mehr genau erinnerst kannst du auf \"Übung ansehen\" klicken um die Übung erneut zu machen.");
        }

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
    public void onClick(View view) {
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        editor.putBoolean("WürfelSave", true);
        switch(view.getId()){
            case R.id.wuerfeln_button:
                //Würfeln
                Random rand = new Random();
                augenzahl = rand.nextInt(6) + 1;
                titelZahl = String.valueOf(augenzahl);
                switch(augenzahl){
                    case 1:
                        wochentag = "Montag";
                        titelBild = R.drawable.wuerfel1;
                        break;

                    case 2:
                        wochentag = "Dienstag";
                        titelBild = R.drawable.wuerfel2;
                        break;

                    case 3:
                        wochentag = "Mittwoch";
                        titelBild = R.drawable.wuerfel3;
                        break;

                    case 4:
                        wochentag = "Donnerstag";
                        titelBild = R.drawable.wuerfel4;
                        break;

                    case 5:
                        wochentag = "Freitag";
                        titelBild = R.drawable.wuerfel5;
                        break;

                    case 6:
                        wochentag = "Samstag";
                        titelBild = R.drawable.wuerfel6;
                        break;

                    default:
                        break;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(HausaufgabeWuerfel.this);
                builder.setTitle(titelZahl);
                builder.setIcon(titelBild);
                builder.setMessage("Du hast eine " + augenzahl + " gewürfelt.\nProbiere am nächsten " + wochentag + " doch einmal ein verrücktes Verhalten aus.\nWie fühlt sich das an?\nBekommst du vielleicht positive Reaktionen auf dein neues Verhalten?");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogX = builder.create();
                dialogX.show();


            break;

            case R.id.wuerfel_Button1:
                onBackPressed();
                break;

            case R.id.wuerfel_Button2:
                startActivity(new Intent(this, Level2Universalloesung.class));
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
