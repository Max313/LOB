package com.example.lammel.lob;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Verhalten extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Buttons and more
    private Button weiter;
    private Boolean aenderung = false;
    private AppCompatDelegate delegate;
    private EditText txt1, txt2, txt3;
    private TableLayout table;
    private int counter;
    private Button add;
    List<EditText> allEds;
    List<String> texts;

    //Tracker
    private Tracker mTracker;
    private final static String TAG = "Verhalten";
    private long start;
    private long end;


    //Tabelleninhalt
    private String v1, v2, v3;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verhalten);
        this.setTitle(" Ressourcentabelle");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.verhalten, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_verhalten);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level3));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.quelle);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);


        // Get tracker.
        ApplicationAnalytics application = (ApplicationAnalytics) getApplication();
        mTracker = application.getDefaultTracker();

        //logging
        start = System.currentTimeMillis();
        Log.i(TAG,"Start: "+start);


        //Buttons and more in action
        weiter = (Button) findViewById(R.id.weiterzuKompliment_Button);
        weiter.setEnabled(false);
        weiter.setOnClickListener(this);

        add = (Button) findViewById(R.id.addRowV_Button);
        add.setOnClickListener(this);

        table = (TableLayout) findViewById(R.id.Table_Verhalten);

        allEds = new ArrayList();
        texts = new ArrayList();


        //Tabelle befüllen falls nötig
        saved = getSharedPreferences(PREFS_NAME, 0);

        v1 = saved.getString("Verhalten1", "");
        texts.add(v1);
        v2 = saved.getString("Verhalten2", "");
        texts.add(v2);
        v3 = saved.getString("Verhalten3", "");
        texts.add(v3);

        counter = saved.getInt("VerhaltenCounter", 3);
        if(counter > 3){
            setUpView();
        }

        txt1 = (EditText) findViewById(R.id.verhalten1EditText);

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

        txt2 = (EditText) findViewById(R.id.verhalten2EditText);

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

        txt3 = (EditText) findViewById(R.id.verhalten3EditText);

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



        if(v1 != "" || v2 != "" || v3 != ""){

            txt1.setText(v1);
            txt2.setText(v2);
            txt3.setText(v3);

            weiter.setEnabled(true);  //enable
        }

        enableButton();

    }


    //add TextChangeListener to each EditText and enable the button
    public void enableButton(){

        txt1.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt1.length() == 0 && txt2.length() == 0 && txt3.length() == 0) {
                    weiter.setEnabled(false); //disable button if no text entered
                }
                else {
                    weiter.setEnabled(true);  //otherwise enable
                    v1 = txt1.getText().toString().trim();
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
                if(txt1.length() == 0 && txt2.length() == 0 && txt3.length() == 0) {
                    weiter.setEnabled(false); //disable button if no text entered
                }
                else {
                    weiter.setEnabled(true);  //otherwise enable
                    v2 = txt2.getText().toString().trim();
                }

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
                if(txt1.length() == 0 && txt2.length() == 0 && txt3.length() == 0) {
                    weiter.setEnabled(false); //disable button if no text entered
                }
                else{
                    weiter.setEnabled(true);  //otherwise enable
                    v3 = txt3.getText().toString().trim();

                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }

    // Set up the View
    public void setUpView(){
        saved = getSharedPreferences(PREFS_NAME, 0);
        //if there are more than 3 entries EditViews are added for each entry
       for(int i = 4; i<= counter; i++){
           String st = "Verhalten"+i;
           TableRow tr = new TableRow(this);

           EditText eTxt = new EditText(this);

           if(i % 2 == 0) {
               eTxt.setBackgroundResource(R.drawable.table_value_border_even);
           }
           else{
               eTxt.setBackgroundResource(R.drawable.table_value_border_odd);
           }

           int paddingDp = getResources().getDimensionPixelOffset(R.dimen.smallSpace);


           eTxt.setPadding(paddingDp, 0, paddingDp,0);
           eTxt.setText(saved.getString(st, ""));
           TableRow.LayoutParams params = (new TableRow.LayoutParams(0,TableRow.LayoutParams.MATCH_PARENT,1.0f));
           eTxt.setLayoutParams(params);
           eTxt.setSingleLine(false);
           eTxt.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
           eTxt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
           eTxt.setVerticalScrollBarEnabled(true);
           eTxt.setMovementMethod(ScrollingMovementMethod.getInstance());
           eTxt.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
           allEds.add(eTxt);
           texts.add(i-1, saved.getString(st, ""));
           tr.addView(eTxt);
           table.addView(tr);
           setKeyboardOptions();
           addChangeListener();
        }
    }

    //add a new Row after pressing the + Button
    public void addRow(){
        counter++;
        TableRow tr = new TableRow(this);
        final EditText eTxt = new EditText(this);
        if(counter % 2 == 0) {
            eTxt.setBackgroundResource(R.drawable.table_value_border_even);
        }
        else{
            eTxt.setBackgroundResource(R.drawable.table_value_border_odd);
        }

        eTxt.setHint("Eingabe " + counter);


        int paddingDp = getResources().getDimensionPixelOffset(R.dimen.smallSpace);
        eTxt.setPadding(paddingDp, 0, paddingDp,0);
        eTxt.setId(counter);
        TableRow.LayoutParams params = (new TableRow.LayoutParams(0,TableRow.LayoutParams.MATCH_PARENT,1.0f));
        eTxt.setLayoutParams(params);

        eTxt.setSingleLine(false);
        eTxt.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        eTxt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        eTxt.setVerticalScrollBarEnabled(true);
        eTxt.setMovementMethod(ScrollingMovementMethod.getInstance());
        eTxt.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        allEds.add(eTxt);
        tr.addView(eTxt);
        table.addView(tr);
        addChangeListener();

        eTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

    //Add Change Listener to the new Rows
    public void addChangeListener(){
        for(int i = 0; i<allEds.size(); i++){

            final int is = i+3;

            final EditText ed = allEds.get(i);
              ed.addTextChangedListener(new TextWatcher()
            {
                public void afterTextChanged(Editable s){
                    add.setEnabled(true);
                    weiter.setEnabled(true);
                    if(texts.size() > is){
                    texts.set(is, ed.getText().toString().trim());
                }
                    else{
                        texts.add(is,ed.getText().toString().trim());
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after){
                }
                public void onTextChanged(CharSequence s, int start, int before, int count){
                }
            });
        }
    }

//Set Keyboard Options for each new Row
    public void setKeyboardOptions(){
        for(int i = 0; i< allEds.size(); i++){
            allEds.get(i).setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

    }


    //Welche Menüoptionen sind enabled
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        saved = getSharedPreferences(PREFS_NAME, 0);

        menu.findItem(R.id.action_help).setVisible(true);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Verhalten.this);
                builder.setTitle("Beispiele:");
                builder.setMessage("\u2022 Ich bleibe in Konfliktsituationen ruhig\n" +
                        "\n" +
                        " \u2022 Ich stehe zu meinen eigenen Schwächen");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV1 = builder.create();
                dialogV1.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Delete all existing files from the Sun Level
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

    //Start the App from the beginning after pressing delete
    public void startNew(){
        startActivity(new Intent(this, MainActivity.class));
    }



    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Verhalten.this);
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        switch (view.getId()) {

            case R.id.weiterzuKompliment_Button:
                //logging
                end = System.currentTimeMillis();
                Log.i(TAG,"Duration: "+(end -start));

                //Zieht die Einträge nach oben, sollte eine Zeile ausgelassen worden sein
                if(v1.length() == 0){
                    if (v2.length() == 0) {
                        v1 = v3;
                        v3 = "";
                    }
                    else{
                        if(v3.length() != 0){
                            v1 = v2;
                            v2 = v3;
                            v3 = "";
                        }
                        else{
                            v1 = v2;
                            v2 = "";
                        }
                    }
                }
                else if(v2.length() == 0){
                    if (v3 != ""){
                        v2  = v3;
                        v3 = "";
                    }
                }

                //Send Events to Google Analytics
                if(v1.length()!=0){
                    mTracker.send(new HitBuilders.EventBuilder("Verhalten", "Input1").build());
                }
                if(v2.length() !=0){
                    mTracker.send(new HitBuilders.EventBuilder("Verhalten", "Input2").build());
                }


                if(v3.length() != 0){
                    mTracker.send(new HitBuilders.EventBuilder("Verhalten", "Input3").build());
                }

                editor.putString("Verhalten1", v1);
                editor.putString("Verhalten2", v2);
                editor.putString("Verhalten3", v3);

                //Deletes added rows which are not filled
                if(texts.size() < allEds.size()+3){
                    counter = counter - ((allEds.size()+3)-texts.size());
                }

                //Safe entrys beside the first three
                for(int i = 3; i < texts.size(); i++){
                    String string = "Verhalten"+(i+1);
                    //Send Event to Google Analytics
                    mTracker.send(new HitBuilders.EventBuilder("Verhalten", "Input"+(i+1)).build());
                    editor.putString(string, texts.get(i));

                }
                editor.putInt("VerhaltenCounter", counter);

                //Set the destination dependent from the source
                aenderung = saved.getBoolean("TabelleÄndern", false);
                if(!aenderung){
                    startActivity(new Intent(this, Kompliment.class));
                }

                else {
                    editor.putBoolean("TabelleÄndern", false);
                    startActivity(new Intent(this, UebersichtTable.class));
                }
                break;

            case R.id.addRowV_Button:
                addRow();
                add.setEnabled(false);
                break;

            default:
                break;

        }
        editor.apply();
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
