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

import static java.security.AccessController.getContext;

public class Level2Ausnahmen extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;



    //Buttons and more
    private Button ausnahmenWeiter;
    private TextView ausnahmenText;
    private AppCompatDelegate delegate;
    private int counter = 0;

    //Shared Preferences als Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    //erster Lösungsweg
    //Hier wird gefragt ob das Problem in manchen Situationen nicht vorkommt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_ausnahmen);
        this.setTitle("LOB - Lösungswege");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level2_ausnahmen, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level2_ausnahmen);

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


        //Buttons and more
        ausnahmenText = (TextView) findViewById(R.id.ausnahmen_Textview);
        ausnahmenWeiter = (Button) findViewById(R.id.ausnahmen_ButtonWeiter);
        ausnahmenWeiter.setOnClickListener(this);

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
                startActivity(new Intent(this, MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ausnahmen_ButtonWeiter:
                if (counter == 0){
                    ausnahmenText.setText("Gab es in den letzten Wochen irgendwann Zeiten, in denen du den Konflikt weniger schimm erlebt hast?\nWas genau war da anders als sonst?\nWer einen Schatz finden will, muss genau hinschauen.\nNimm dir daher Zeit dich mit den unterschiedlichen Facetten einer Ausnahme auseinanderzusetzen.");
                    counter++;
                    break;
                }
                else if (counter == 1){
                    Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
                    intent.putExtra("LoesungsCounter", 1);
                    startActivity(intent);
                    counter = 0;
                    break;
                }

            case R.id.back_Button:
                if(counter == 0){
                    startActivity(new Intent(this, Level2Veraenderung.class));
                    break;
                }
                else if(counter == 1){
                    ausnahmenText.setText("Gab es in den letzten Wochen irgendwann Zeiten, in denen du den Konflikt weniger schimm erlebt hast?\nWas genau war da anders als sonst?\nWer einen Schatz finden will, muss genau hinschauen.\nNimm dir daher Zeit dich mit den unterschiedlichen Facetten einer Ausnahme auseinanderzusetzen.");
                    counter--;
                    break;
                }

            case R.id.forward_Button:
                if(counter == 0){
                    ausnahmenText.setText("Gab es in den letzten Wochen irgendwann Zeiten, in denen du den Konflikt weniger schimm erlebt hast?\nWas genau war da anders als sonst?\nWer einen Schatz finden will, muss genau hinschauen.\nNimm dir daher Zeit dich mit den unterschiedlichen Facetten einer Ausnahme auseinanderzusetzen.");
                    counter++;
                    break;
                }

                else if (counter == 1){
                    Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
                    intent.putExtra("LoesungsCounter", 1);
                    startActivity(intent);
                    counter = 0;
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
