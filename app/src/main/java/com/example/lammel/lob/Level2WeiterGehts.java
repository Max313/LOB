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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Level2WeiterGehts extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Buttons
    private Button button1, button2;
    private int loesungsCounter;
    private AppCompatDelegate delegate;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_weiter_gehts);
        this.setTitle("LOB - Lösungswege");

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
        delegate.setSupportActionBar(toolbar);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weiterGehts_Button1:
                Intent intent1 = new Intent(v.getContext(), Wunderbar.class);
                intent1.putExtra("LoesungsCounter", loesungsCounter);
                startActivity(intent1);
                break;

            case R.id.weiterGehts_Button2:
                if(loesungsCounter == 0){
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 0);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 1){
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 1);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 2){
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 2);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 3) {
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 3);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 4) {
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 4);
                    startActivity(intent);
                    break;
                }

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
