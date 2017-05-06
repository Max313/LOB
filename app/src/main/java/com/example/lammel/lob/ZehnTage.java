package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
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

public class ZehnTage extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    TextView timer;
    Button fertig;
    CountDownTimer countdown;

    //Toolbar
    private AppCompatDelegate delegate;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    //Während den 10 Tagen Wartezeit kann man Liste zusammenstellen von Dingen auf die man besonders stolz ist
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zehn_tage);

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.zehnTage, fragment);
        transaction.commit();

        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_zehn_tage);

        //Add the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        fertig = (Button) findViewById(R.id.zehnTage_Button);
        fertig.setEnabled(false);
        fertig.setOnClickListener(this);
        //Timer der 10 Tage runterläuft 864000000 ms
        //Timer der 1 Min runterläuft 60000 ms
        countdown = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer = (TextView) findViewById(R.id.zehnTage_Textview2);
                timer.setText("Tage verbleibend: " + (millisUntilFinished / 86400000+1));
            }

            public void onFinish() {
                timer.setText("Geschafft!");
                fertig.setEnabled(true);
            }
        }.start();
;



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
        Intent intent = new Intent(view.getContext(), Level2Loesungswege.class);
        intent.putExtra("LoesungsCounter", 4);
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
