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
import android.widget.ImageButton;
import android.widget.TextView;



public class MenuHausaufgabe extends FragmentActivity implements View.OnClickListener, AppCompatCallback {



    //Buttons
    private Button hausaufgabe1, hausaufgabe2, hausaufgabe3, zurueck;
    private AppCompatDelegate delegate;

    //shared Preferences
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_hausaufgabe);
        this.setTitle("LOB - Hausaufgaben");


        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.menu_hausaufgabe, fragment);
        transaction.commit();

        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_menu_hausaufgabe);

        //Add the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);


        //Buttons und Funktion
        saved = getSharedPreferences(PREFS_NAME, 0);

        hausaufgabe1 = (Button) findViewById(R.id.hausaufgabe_ButtonDiary);
        if(saved.getBoolean("TagebuchSave", false) != true){
            hausaufgabe1.setEnabled(false);
        }
        hausaufgabe1.setOnClickListener(this);

        hausaufgabe2 = (Button) findViewById(R.id.hausaufgabe_ButtonCube);
        if(saved.getBoolean("WürfelSave", false) != true){
            hausaufgabe2.setEnabled(false);
        }
        hausaufgabe2.setOnClickListener(this);

        hausaufgabe3 = (Button) findViewById(R.id.hausaufgabe_ButtonCoin);
        if(saved.getBoolean("MünzeSave", false) != true){
            hausaufgabe3.setEnabled(false);
        }
        hausaufgabe3.setOnClickListener(this);

        zurueck = (Button) findViewById(R.id.hausaufgabe_ButtonBack);
        zurueck.setOnClickListener(this);




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
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().clear().commit();
                startNew();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startNew(){
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuHausaufgabe.this);
        switch(view.getId()){
            case R.id.hausaufgabe_ButtonDiary:
                builder.setTitle("Tagebuch");
                builder.setMessage("Du hast schon viel Arbeit in den Wandel gesteckt. Lass uns die nächsten Treffen dazu nutzen mehr von dem zu tun, was funktioniert. " +
                        "Schau genau hin was, verbessert sich noch alles?\nBesonders effektiv ist es, wenn du dir am Abend, nachdem du einen Lösungsweg ausprobiert hast, ein paar Notizen darüber machst, welche positiven Veränderungen du bemerkt hast. Viel Spaß dabei!" +
                        "\n");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                dialogV.show();
                break;

            case R.id.hausaufgabe_ButtonCube:
                builder.setTitle("Würfelspiel");
                builder.setMessage("Wie mit allem: Übung macht den Meister! Daher ein kleines Spiel dazu: " +
                        "\nWürfle einen Würfel. Kommt eine gerade Zahl raus, dann wende das neue Verhalten in einer Problemsituation an. Kommt eine ungerade heraus so bleibt alles bisher. " +
                        "\nBeobachte ganz genau was passiert.\n");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogX = builder.create();
                dialogX.show();
                break;


            case R.id.hausaufgabe_ButtonCoin:
                builder.setTitle("Münzwurf");
                builder.setMessage("Bei der Phantasiereise hast du eine Welt ohne dein Problem kennengelernt! Also gibt es jetzt ein keines Experiment dazu:  " +
                        "\nBevor du mit einer anderen Person in Berührung kommst werfe eine Münze und wenn sie Kopf zeigt, dann tust du ein klein wenig so als wäre das Wunder schon geschehen. Bei Zahl lässt du alles wie bisher. " +
                        "\nAchte genau darauf wie andere Personen darauf reagieren\n");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogY = builder.create();
                dialogY.show();
                break;

            case R.id.hausaufgabe_ButtonBack:
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
