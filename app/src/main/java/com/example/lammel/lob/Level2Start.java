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

public class Level2Start extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Button and more
    private Button level2Weiter;
    private AppCompatDelegate delegate;

    //Shared Preferences als Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_start);
        this.setTitle("LOB - Lösungswege");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level2_start, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level2_start);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        level2Weiter = (Button) findViewById(R.id.level2Start_Button);
        level2Weiter.setOnClickListener(this);
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
            Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
            intent.putExtra("LoesungsCounter", 0);
            startActivity(intent);
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