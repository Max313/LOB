package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by lammel on 11.04.17.
 */

public class Level1Onboarding extends FragmentActivity implements View.OnClickListener, AppCompatCallback {


    private int counter = 0;

    // Button and more
    private Button weiter_button;
    private TextView onboard;
    private AppCompatDelegate delegate;

    //shared Preferences zum Speichern
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;


    //Heranführen an das Thema Lösungsorientierte Beratung
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1_onboarding);
        this.setTitle("LOB - Family Treasure");

        //Set Status - Footer
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        editor.putInt("tabStatus", 0);
        editor.apply();

        //Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level1_onboarding, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.level1_onboarding);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        onboardingProzessStarten();

    }



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

    private void onboardingProzessStarten() {
        weiter_button = (Button) findViewById(R.id.weiter_button);
        onboard = (TextView) findViewById(R.id.onboard_1);
        weiter_button.setOnClickListener(this);
    }

    public void startNew(){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.weiter_button:
            if (counter == 0) {
                onboard.setText("Du bist der Meinung dein Umfeld ist wichtig und gibt Energie?\nFinden wir auch!\nLerne hier deine Konflikte zu lösen.");
                counter++;
                break;
            } else if (counter == 1) {
                onboard.setText("Die Ursache des Konflikts ist nicht entscheidend, wichtig ist, wie du die Situation in der Zukunft siehst.");
                counter++;
                break;
            } else if (counter == 2) {
                onboard.setText("Deswegen spielt das Problem hier eine untergeordnete Rolle.\nEs geht darum, dass du dich gut fühlst und das erreicht man nicht durch die Problem- sondern Lösungsfokussierung.");
                counter++;
                break;
            } else if (counter == 3) {
                startActivity(new Intent(this, Level1Problemdefinition.class));

                //startActivity(new Intent(this, Staerkeinsel.class));

                //startActivity(new Intent(this, Rueckblick.class));

                //startActivity(new Intent(this, SonneDerErkenntnisStart.class));
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
