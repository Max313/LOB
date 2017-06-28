package com.example.lammel.lob;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class Level4InselFrage1 extends FragmentActivity implements View.OnClickListener, AppCompatCallback {


    //Buttons and more
    private Button weiter;
    private EditText frage1;
    private String eingabe = "Eingabemöglichkeit";
    private String eingabe1;
    private AppCompatDelegate delegate;

    private final static String TAG = "InselFrage1";
    private long start;
    private long end;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_insel_frage1);
        this.setTitle("Insel des Sehenden");

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
        delegate.setContentView(R.layout.activity_level4_insel_frage1);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level4));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.sonnevoll);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        //logging
        start = System.currentTimeMillis();
        Log.i(TAG,"Start: "+start);

        //Button on action
        weiter = (Button) findViewById(R.id.frage1_Button);
        weiter.setOnClickListener(this);

        //Edit Text + sharedPreference
        saved = getSharedPreferences(PREFS_NAME, 0);
        eingabe1 = saved.getString("Frage1Save", eingabe);
        frage1 = (EditText) findViewById(R.id.frage1_editView);
        frage1.setHorizontallyScrolling(false);
        frage1.setLines(6);
        frage1.setHint(eingabe1);
        frage1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

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
        frage1 = (EditText) findViewById(R.id.frage1_editView);
        saved = getSharedPreferences(PREFS_NAME,0);
        editor = saved.edit();
        if(frage1.getText().toString().length() != 0){
            editor.putString("Frage1Save", frage1.getText().toString());
            editor.apply();
        }
        //logging
        end = System.currentTimeMillis();
        Log.i(TAG,"Duration: "+(end - start));
                startActivity(new Intent(this, Level4InselFrage2.class));



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
