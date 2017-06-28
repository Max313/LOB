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

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;

public class Level2Loesungswege extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Viewteile der Activity (Buttons, Textviews,..)
    private Button fertig;
    private Button mirFaelltNichtsEin;
    private TextView anfangsText;
    private int loesungsCounter;
    private AppCompatDelegate delegate;
    private Boolean txt1leer, txt2leer, txt3leer;
    private EditText txt1, txt2, txt3;

    private Tracker mTracker;
    private final static String TAG = "Lösungswege";
    private long start;
    private long end;

    //shared Preferences als Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;
    private String weg1, weg2, weg3;

    //hier kann man Lösungswege angeben die man ausprobieren kann
    //findet sich eine Lösung so kann man weiter zu Level 3
    //findet sich noch kein Lösungsweg so werden neue Wege angeboten
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_loesungswege);
        this.setTitle("Lösungswege");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level2_loesungswege, fragment);
        transaction.commit();

        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();

        if(saved.getInt("zielStatus", 0) < 2){
            editor.putInt("zielStatus", 2);
        }

        else if(saved.getInt("ideeStatus",0) < 1){
            editor.putInt("ideeStatus", 1);
        }

        editor.putInt("tabStatus", 2);
        editor.apply();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level2_loesungswege);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level2));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.wegweiserb);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Get tracker und Logging
        ApplicationAnalytics application = (ApplicationAnalytics) getApplication();
        mTracker = application.getDefaultTracker();

        //logging
        start = System.currentTimeMillis();
        Log.i(TAG,"Start: "+start);


        //Action and Hausaufgaben freischalten
        loesungsCounter = getIntent().getExtras().getInt("LoesungsCounter");
        anfangsText = (TextView) findViewById(R.id.textView4);
        AlertDialog.Builder builder = new AlertDialog.Builder(Level2Loesungswege.this);
        switch(loesungsCounter){
            case 1:
                anfangsText.setText("Trotz deines Problems hast du so lange durchgehalten. Was hat dir geholfen diese Energie immer wieder aufs Neue aufzubringen? Das kann dir auch in Zukunft weiterhelfen.");
                break;

            case 2:
                anfangsText.setText("Es ist nicht einfach solch eine Ausnahme zu finden. Hoffentlich hat dir diese Übung dabei geholfen einen neuen Blickwinkel einzunehmen und dadurch auf neue Lösungswege zu kommen.");
                break;

            case 3:
                anfangsText.setText("Das Leben kann anders sein als es momentan scheint. Hast du eine Idee bekommen, auf welchem Weg du deine Zukunft positiv beeinflussen kannst?");
                break;

            case 4:
                anfangsText.setText("Oft kann eine kleine Veränderung des Verhaltens große Wirkung zeigen. Ist dir eine Möglichkeit eingefallen um eine positive Veränderung zu erzielen?");
                break;

            case 5:
                anfangsText.setText("Auch wenn sich dein Problem übermächtig anfühlt gibt es bestimmt Sachen, die in deinem Leben positiv laufen. Woraus schöpfst du Energie und was macht dir Freude?");
                //Notification Alarm wieder erlauben
                editor.putBoolean("alarmStart", false);
                editor.apply();
                break;

            case 6:
                anfangsText.setText("Klicke einfach auf den Weiter-Button wenn du die App weiter erforschen willst und vielleicht tun sich zu einem späteren Zeitpunkt noch Lösungswege auf.");
                break;

            default:
                break;
        }

        //Buttons, Speicher, EditTexts
        saved = getSharedPreferences(PREFS_NAME, 0);
        weg1 = saved.getString("loesungsweg1", "");
        weg2 = saved.getString("loesungsweg2", "");
        weg3 = saved.getString("loesungsweg3", "");

        mirFaelltNichtsEin = (Button) findViewById(R.id.loesungswege_ButtonNichts);
        mirFaelltNichtsEin.setOnClickListener(this);
        mirFaelltNichtsEin.setEnabled(false);
        fertig = (Button) findViewById(R.id.loesungswege_ButtonFertig);

        if(saved.getBoolean("FertigSaved", false) || weg1.length() != 0 || weg2.length() != 0 || weg3.length() != 0){
            mirFaelltNichtsEin.setText("Neue Wege");
        }
        if(loesungsCounter != 6){
            fertig.setEnabled(false);
            mirFaelltNichtsEin.setEnabled(true);
        }

        fertig.setOnClickListener(this);

        txt1 = (EditText) findViewById(R.id.loesungswege_edittext1);
        if(weg1 != ""){
            txt1.setText(weg1);
            fertig.setEnabled(true);
        }

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

        txt2 = (EditText) findViewById(R.id.loesungswege_edittext2);
        if(weg2 != ""){
            txt2.setText(weg2);
            fertig.setEnabled(true);
        }

        txt2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

        txt3 = (EditText) findViewById(R.id.loesungswege_edittext3);
        if(weg3 != ""){
            txt3.setText(weg3);
            fertig.setEnabled(true);
        }

        txt3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

        //checkt ob Text geschrieben wurde
        enableButton();
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
        if (!saved.getBoolean("MenuHausaufgabe", false)){
            menu.findItem(R.id.Hausaufgabe).setEnabled(false);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Level2Loesungswege.this);

                if(saved.getBoolean("FertigSaved", false)){
                    builder.setTitle("Lösungsweg - Beispiel");
                    builder.setMessage("Ein Problem könnte sein, dass du dich gestresst fühlst und du diese App gestartet hast mit dem Ziel, dich im Alltag entspannter zu fühlen.\nEin möglicher Lösungsweg wäre \"Ich halte mir eine bestimmte Zeit am Tag frei, in der ich keine Termine plane\" oder \"Ich schalte mein Handy eine Stunde pro Tag aus\".\nMit Hilfe der Übungen findest du den richtigen Weg für dich. Klicke \"Mir fällt nichts ein\" um dorthin zu gelangen.");
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialogX = builder.create();
                    dialogX.show();
                }
                else{
                    builder.setTitle("Lösungsweg - Beispiel");
                    builder.setMessage("Ein Problem könnte sein, dass du dich gestresst fühlst und du diese App gestartet hast mit dem Ziel, dich im Alltag entspannter zu fühlen.\nEin möglicher Lösungsweg wäre \"Ich halte mir eine bestimmte Zeit am Tag frei, in der ich keine Termine plane\" oder \"Ich schalte mein Handy eine Stunde pro Tag aus\".\nMit Hilfe der Übungen findest du den richtigen Weg für dich. Klicke \"Neue Wege\" um dorthin zu gelangen.");
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialogX = builder.create();
                    dialogX.show();
                }

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

    public void enableButton(){

        txt1.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt1.length() == 0) {
                    fertig.setEnabled(false); //disable button if no text entered
                }
                else {
                    fertig.setEnabled(true);  //otherwise enable
                    mTracker.send(new HitBuilders.EventBuilder("Lösungswege", "Input1").build());

                    weg1 = txt1.getText().toString();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });


        txt2.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt2.length() == 0)
                    fertig.setEnabled(false); //disable button if no text entered
                else{
                    fertig.setEnabled(true);
                    mTracker.send(new HitBuilders.EventBuilder("Lösungswege", "Input2").build());

                    //otherwise enable
                weg2 = txt2.getText().toString();}

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });


        txt3.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt3.length() == 0) {
                    fertig.setEnabled(false); //disable button if no text entered
                }
                else {
                    fertig.setEnabled(true);  //otherwise enable
                    mTracker.send(new HitBuilders.EventBuilder("Lösungswege", "Input3").build());

                    weg3 = txt3.getText().toString();
                }

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }



    @Override
    public void onClick(View v) {
        //logging
        end = System.currentTimeMillis();
        Log.i(TAG,"Duration: "+(end - start));

        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        editor.putString("loesungsweg1", weg1);
        editor.putString("loesungsweg2", weg2);
        editor.putString("loesungsweg3", weg3);
        editor.apply();

        switch (v.getId()) {
            case R.id.loesungswege_ButtonFertig:

                editor.putBoolean("FertigSaved", true);
                editor.apply();
                if(loesungsCounter == 0){
                    Intent intent = new Intent(v.getContext(), Level2WeiterGehts.class);
                    intent.putExtra("LoesungsCounter", 0);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 1){
                    Intent intent = new Intent(v.getContext(), Level2WeiterGehts.class);
                    intent.putExtra("LoesungsCounter", 1);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 2){
                    Intent intent = new Intent(v.getContext(), Level2WeiterGehts.class);
                    intent.putExtra("LoesungsCounter", 2);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 3) {
                    Intent intent = new Intent(v.getContext(), Level2WeiterGehts.class);
                    intent.putExtra("LoesungsCounter", 3);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 4) {
                    Intent intent = new Intent(v.getContext(), Level2WeiterGehts.class);
                    intent.putExtra("LoesungsCounter", 4);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 5) {
                    Intent intent = new Intent(v.getContext(), Level2WeiterGehts.class);
                    intent.putExtra("LoesungsCounter", 5);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 6) {
                    Intent intent = new Intent(v.getContext(), Level2WeiterGehts.class);
                    intent.putExtra("LoesungsCounter", 6);
                    startActivity(intent);
                    break;
                }

            case R.id.loesungswege_ButtonNichts:
                if(loesungsCounter == 0){
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 0);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2Veraenderung.class));

                    break;
                }
                if(loesungsCounter == 1){
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 0);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2Ausnahmen.class));

                    break;
                }
                else if(loesungsCounter == 2){
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 1);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2HypoLoesung.class));
                    break;
                }
                else if(loesungsCounter == 3){
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 2);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2Universalloesung.class));
                    break;
                }
               else if(loesungsCounter == 4) {
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 3);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2Exitstrategie.class));
                    break;
                }
                else if(loesungsCounter == 5) {
                    //Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    //intent.putExtra("WegCounter", 4);
                    //startActivity(intent);
                    startActivity(new Intent(this, Level2KeineLoesung.class));
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
