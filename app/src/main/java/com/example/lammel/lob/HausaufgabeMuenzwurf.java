package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * In dieser Hausaufgabe wird eine Münze geworden, der Nutzer hat je nach Kopf/Zahl eine Aufgabe anhand Level2HypoLoesung
 */

public class HausaufgabeMuenzwurf extends FragmentActivity implements View.OnClickListener, AppCompatCallback {



    //Buttons
    private Button zuAufgabe, zurueck, werfen;
    private TextView txt;
    private AppCompatDelegate delegate;
    private int wurf;


    //shared Preferences
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hausaufgabe_muenzwurf);
        this.setTitle("Hausaufgaben");


        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.hausaufgabe_muenzwurf, fragment);
        transaction.commit();

        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_hausaufgabe_muenzwurf);

        //Add the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);


        //Buttons und Funktion
        werfen = (Button) findViewById(R.id.muenzwurf);
        werfen.setOnClickListener(this);

        zuAufgabe = (Button) findViewById(R.id.muenzwurfAufgabe_Button);
        zuAufgabe.setOnClickListener(this);

        zurueck = (Button) findViewById(R.id.muenzwurfZurueck_Button);
        zurueck.setOnClickListener(this);

        //Text -> Übung wurde schon gemacht oder noch nicht?
        saved = getSharedPreferences(PREFS_NAME, 0);

        txt = (TextView) findViewById(R.id.muenzwurf_Text);
        if(saved.getBoolean("MünzeSave", false)){
            txt.setText("Bei Lösungsweg 3 hast du eine Welt ohne dein Problem kennengelernt!\nPassend dazu gibt es ein kleines Experiment:\nBevor du in die Situation kommst, in der dein Problem normalerweise auftritt, klicke auf \"Münze werfen\" oder werfe eine echte Münze.\nWenn sie Kopf zeigt, dann tust du ein klein wenig so als wäre das Wunder schon geschehen.\nBei Zahl lässt du alles wie bisher.\nAchte genau darauf wie dich dabei fühlst.\nFalls du dir unsicher bist, kannst du dir die Übung auch erneut ansehen, indem du auf \"Übung ansehen\" klickst.");
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
            case R.id.muenzwurf:
                AlertDialog.Builder builder = new AlertDialog.Builder(HausaufgabeMuenzwurf.this);
                Random rand = new Random();
                wurf = rand.nextInt(2) + 1;
                if(wurf ==1){
                    builder.setTitle("Kopf");
                    builder.setIcon(R.drawable.muenzekopf);
                    builder.setMessage("Die Münze zeigt Kopf an.\nDas Wunder ist geschehen.\nBeobachte genau was sich in der Situation alles verändert, wenn du so tust als wäre dein Problem verschwunden.\nWie fühlst du dich dabei?");
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialogX = builder.create();
                    dialogX.show();
                }
                else{
                    builder.setTitle("Zahl");
                    builder.setIcon(R.drawable.muenzezahl);
                    builder.setMessage("Die Münze zeigt Zahl an.\nNoch ist kein Wunder geschehen.\nAchte trotzdem ganz genau darauf, wie du dich fühlst, wenn du in dieser Situation bist.\nWas würdest du gerne ändern?");
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialogX = builder.create();
                    dialogX.show();
                }


                break;

            case R.id.muenzwurfAufgabe_Button:
                startActivity(new Intent(this, Level2HypoLoesung.class));
                break;

            case R.id.muenzwurfZurueck_Button:
                onBackPressed();
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

