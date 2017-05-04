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

public class Level4InselFragen extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;

    //Buttons and more
    private int counter = 0;
    private Button weiter;
    private TextView txt;
    private AppCompatDelegate delegate;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_insel_fragen);
        this.setTitle("LOB - Insel des Sehenden - 1/4");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level4_insel_fragen, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level4_insel_fragen);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);
        forward.setVisibility(View.VISIBLE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.GONE);


        //Button on action
        weiter = (Button) findViewById(R.id.frage1_Button);
        weiter.setOnClickListener(this);
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
                editor.clear();
                editor.apply();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        txt = (TextView) findViewById(R.id.frage1_TextView);

        switch(view.getId()){
            case R.id.frage1_Button:
                if(counter == 0){
                    this.setTitle("LOB - Insel des Sehenden - 2/4");
                    txt.setText("Was ist inzwischen geschehen, von dem du möchtest, dass es weiterhin in dieser Weise geschieht?");
                    counter++;
                    break;
                }

                else if(counter == 1){
                    this.setTitle("LOB - Insel des Sehenden - 3/4");

                    txt.setText("Was hat sich seither schon an Positiven entwickelt, so dass du jetzt das Gefühl hast, einen Schritt weiter zu sein?");
                    counter++;
                    break;
                }

                else if(counter == 2) {
                    this.setTitle("LOB - Insel des Sehenden - 4/4");
                    txt.setText("Was von dem, was sich seit der letzten Nutzung der App verändert hat, hältst du für am wichtigsten?");
                    counter++;
                    break;
                }
                else if(counter == 3){
                    counter = 0;
                    Intent intent = new Intent(view.getContext(), Mantra.class);
                    intent.putExtra("Source", 0);
                    startActivity(intent);
                    break;
                }

            case R.id.back_Button:

                if(counter == 0){
                    startActivity(new Intent(this, Level4InselDesSehenden.class));
                    break;
                }
                else if(counter == 1){
                    startActivity(new Intent(this, Level4InselFragen.class));
                    counter--;
                    break;
                }
                else if(counter == 2){
                    this.setTitle("LOB - Insel des Sehenden - 2/4");
                    txt.setText("Was ist inzwischen geschehen, von dem du möchtest, dass es weiterhin in dieser Weise geschieht?");
                    counter--;
                    break;
                }

                else if(counter == 3) {
                    this.setTitle("LOB - Insel des Sehenden - 3/4");
                    txt.setText("Was hat sich seither schon an Positiven entwickelt, so dass du jetzt das Gefühl hast, einen Schritt weiter zu sein?");
                    counter--;
                    break;
                }


            case R.id.forward_Button:

                if(counter == 0){
                    this.setTitle("LOB - Insel des Sehenden - 2/4");
                    txt.setText("Was ist inzwischen geschehen, von dem du möchtest, dass es weiterhin in dieser Weise geschieht?");
                    counter++;
                    break;
                }

                else if(counter == 1){
                    this.setTitle("LOB - Insel des Sehenden - 3/4");

                    txt.setText("Was hat sich seither schon an Positiven entwickelt, so dass du jetzt das Gefühl hast, einen Schritt weiter zu sein?");
                    counter++;
                    break;
                }

                else if(counter == 2) {
                    this.setTitle("LOB - Insel des Sehenden - 4/4");
                    txt.setText("Was von dem, was sich seit der letzten Nutzung der App verändert hat, hältst du für am wichtigsten?");
                    counter++;
                    break;
                }
                else if(counter == 3){
                    counter = 0;
                    Intent intent = new Intent(view.getContext(), Mantra.class);
                    intent.putExtra("Source", 0);
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
