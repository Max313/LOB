package com.example.lammel.lob;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Level2UniversalloesungWeiter extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Buttons and more
    private Button universalloesungWeiter_Weiter, universalloesungWeiter_Nichts;
    private AppCompatDelegate delegate;
    private String universal;
    private int counter = 0;
    private int storyCounter;

    //shared Preferences
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;


    //dritter Lösungsweg
    //gib eine Möglichkeit an für so eine Universallösung
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_universalloesung_weiter);
        this.setTitle("Lösungsweg 4");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level2_universalloesung_weiter, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level2_universalloesung_weiter);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level2));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.mipmap.wegweiser);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Speicher
        saved = getSharedPreferences(PREFS_NAME, 0);

        //Buttons
        universalloesungWeiter_Weiter = (Button) findViewById(R.id.universalWeiter_ButtonWeiter);
        universalloesungWeiter_Weiter.setOnClickListener(this);
        universalloesungWeiter_Weiter.setEnabled(false);

        universalloesungWeiter_Nichts = (Button) findViewById(R.id.universal_Nichts);
        universalloesungWeiter_Nichts.setOnClickListener(this);
        storyCounter = saved.getInt("StoryCounter", 0);
        if(storyCounter>1){
            universalloesungWeiter_Nichts.setEnabled(false);
        }

        //Hausaufgabe schon gesehen?
        if(saved.getBoolean("WürfelSave", false)){
            counter = 1;
        }

        //Edit Text und gespeicherter Text
        universal = saved.getString("UniversalSave", "");
        final EditText txt1 = (EditText) findViewById(R.id.universal_EditText);
        txt1.setHorizontallyScrolling(false);
        txt1.setLines(3);
        txt1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

        if(universal.length() != 0){
            txt1.setText(universal);
            universalloesungWeiter_Weiter.setEnabled(true);
        }
        txt1.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt1.length() == 0)
                    universalloesungWeiter_Weiter.setEnabled(false); //disable button if no text entered
                else
                    universal = txt1.getText().toString();
                    universalloesungWeiter_Weiter.setEnabled(true);  //otherwise enable

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
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
        menu.findItem(R.id.action_help).setVisible(true);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.action_help).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
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

            case R.id.action_help:
                AlertDialog.Builder builder = new AlertDialog.Builder(Level2UniversalloesungWeiter.this);
                builder.setTitle("Lösungsweg 4 - Hilfe");
                builder.setMessage("Wenn du dir nicht sicher bist, was für \"verrückte\" Verhaltensweisen es geben könnte, findest du einige Beispiele wenn du auf \"Mir fällt nichts ein\" klickst.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogX = builder.create();
                dialogX.show();

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
    public void onClick(View v) {
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        editor.putString("UniversalSave", universal);
        editor.apply();
        storyCounter = saved.getInt("StoryCounter", 0);
        final View view = v;
        AlertDialog.Builder builder = new AlertDialog.Builder(Level2UniversalloesungWeiter.this);
        switch (v.getId()) {
            //Text abspeichern und weiter
            case R.id.universalWeiter_ButtonWeiter:
                editor.putInt("StoryCounter", 0);
                editor.putBoolean("WürfelSave", true);
                editor.apply();
                /*if(counter == 0){
                    builder.setTitle("Hausaufgabe");
                    builder.setMessage("Da du eine passende Möglichkeit gefunden hast wäre es spannend zu sehen, wie die Rekationen darauf aussehen. Du hast eine neue Hausaufgabe freigeschaltet.");
                    builder.setPositiveButton(R.string.weiter_Button, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            startNext(view);
                        }
                    });
                    builder.setNeutralButton("Ansehen",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            startHausaufgaben(view);
                        }
                    });
                    AlertDialog dialogZ = builder.create();
                    //Hausaufgabendialog -> zur Zeit ausgeschaltet
                    //dialogZ.show();
                    counter++;
                }
                else{
                */
                    saved = getSharedPreferences(PREFS_NAME, 0);
                    editor = saved.edit();
                    editor.putInt("level2Save", 4);
                    editor.apply();
                    Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
                    intent.putExtra("LoesungsCounter", 4);
                    startActivity(intent);
                //}
                break;

            case R.id.universal_Nichts:
                if(storyCounter==0){
                    editor.putInt("StoryCounter", 1);
                    editor.apply();
                    Intent intent2 = new Intent(v.getContext(), Level2UniversalloesungAnekdote.class);
                    intent2.putExtra("Anekdote2", false);
                    intent2.putExtra("Source", 0);
                    startActivity(intent2);
                }
                else {
                    editor.putInt("StoryCounter", 2);
                    editor.apply();
                    Intent intent3 = new Intent(v.getContext(), Level2UniversalloesungAnekdote.class);
                    intent3.putExtra("Anekdote2", true);
                    intent3.putExtra("Source", 1);
                    startActivity(intent3);
                }
                break;

            default:
                break;
        }
    }

    private void startNext(View v) {
        Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
        intent.putExtra("LoesungsCounter", 4);
        startActivity(intent);
    }

    private void startHausaufgaben(View v) {
        Intent intent = new Intent(v.getContext(), MenuHausaufgabe.class);
        intent.putExtra("Hausaufgabe", 2);
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
