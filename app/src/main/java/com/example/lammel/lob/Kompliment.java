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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
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
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Kompliment extends FragmentActivity implements View.OnClickListener, AppCompatCallback {



    //Tabelleninhalt
    private String k1, k2, k3;

    private Button weiter;
    private Button q;
    private TextView kompliment;
    private AppCompatDelegate delegate;
    private Boolean aenderung = false;
    private EditText txt1, txt2, txt3;
    private TableLayout table;
    private int counter;
    private Button add;
    private List<EditText> allEds;
    private List<String> texts;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kompliment);
        this.setTitle("Ressourcentabelle");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.kompliment, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_kompliment);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level3));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.baum);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Buttons and more in action
        weiter = (Button) findViewById(R.id.weiterzuRessource_Button);
        weiter.setOnClickListener(this);
        weiter.setEnabled(false);

        allEds = new ArrayList<EditText>();
        texts = new ArrayList<String>();

        kompliment = (TextView) findViewById(R.id.komplimentTextView);
        kompliment.setOnClickListener(this);

        add = (Button) findViewById(R.id.addRowK_Button);
        add.setOnClickListener(this);

        table = (TableLayout) findViewById(R.id.Table_Kompliment);


        //Tabelle befüllen falls nötig
        saved = getSharedPreferences(PREFS_NAME, 0);

        k1 = saved.getString("Kompliment1", "");
        texts.add(k1);
        k2 = saved.getString("Kompliment2", "");
        texts.add(k2);
        k3 = saved.getString("Kompliment3", "");
        texts.add(k3);


        counter = saved.getInt("KomplimentCounter", 3);
        if(counter > 3){
            setUpView();
        }

        txt1 = (EditText) findViewById(R.id.kompliment1EditText);
        txt2 = (EditText) findViewById(R.id.kompliment2EditText);
        txt3 = (EditText) findViewById(R.id.kompliment3EditText);

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

        if(k1 != "" || k2 != "" || k3 != ""){

            txt1.setText(k1);
            txt2.setText(k2);
            txt3.setText(k3);

            weiter.setEnabled(true);  //enable
        }

        enableButton();
    }

    public void enableButton(){

        txt1.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt1.length() == 0 && txt2.length() == 0 && txt3.length() == 0)
                    weiter.setEnabled(false); //disable button if no text entered
                else
                    weiter.setEnabled(true);  //otherwise enable
                k1 = txt1.getText().toString().trim();

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
                if(txt1.length() == 0 && txt2.length() == 0 && txt3.length() == 0)
                    weiter.setEnabled(false); //disable button if no text entered
                else
                    weiter.setEnabled(true);  //otherwise enable
                k2 = txt2.getText().toString().trim();

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
                if(txt1.length() == 0 && txt2.length() == 0 && txt3.length() == 0)
                    weiter.setEnabled(false); //disable button if no text entered
                else
                    weiter.setEnabled(true);  //otherwise enable
                k3 = txt3.getText().toString().trim();

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }


    public void setUpView(){
        saved = getSharedPreferences(PREFS_NAME, 0);
        for(int i = 4; i<= counter; i++){
            String st = "Kompliment"+i;
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
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
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        final EditText eTxt = new EditText(this);
        if(counter % 2 == 0) {
            eTxt.setBackgroundResource(R.drawable.table_value_border_even);
        }
        else{
            eTxt.setBackgroundResource(R.drawable.table_value_border_odd);
        }

        eTxt.setHint("Eingabe");
        int paddingDp = getResources().getDimensionPixelOffset(R.dimen.smallSpace);
        eTxt.setPadding(paddingDp, 0, paddingDp,0);
        eTxt.setId(counter);
        eTxt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        eTxt.setInputType(InputType.TYPE_CLASS_TEXT);
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
                startActivity(new Intent(this, SonneDerErkenntnisStart.class));
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Kompliment.this);
                builder.setTitle("Beispiel");
                builder.setMessage("Es ist beeindruckend wie einfühlsam und aufmerksam ich auf meinen Partner eingehe.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK = builder.create();
                dialogK.show();

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
        AlertDialog.Builder builder = new AlertDialog.Builder(Kompliment.this);
        saved = getSharedPreferences(PREFS_NAME, 0);
        aenderung = saved.getBoolean("TabelleÄndern", false);
        editor = saved.edit();
        switch (view.getId()) {

            case R.id.komplimentTextView:

                builder.setTitle("Kompliment");
                builder.setMessage("Gebe dir selbst ein Kompliment, so wie du es auch einem guten Freund geben würdest. \nz.B. Ich gehe umsichtig und überlegt an die Situation heran.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                dialogV.show();
                break;

            case R.id.weiterzuRessource_Button:
                if(k1.length() == 0){
                    if (k2.length() == 0) {
                        k1 = k3;
                        k3 = "";
                    }
                    else{
                        if(k3.length() != 0){
                            k1 = k2;
                            k2 = k3;
                            k3 = "";
                        }
                        else{
                            k1 = k2;
                            k2 = "";
                        }
                    }
                }
                else if(k2.length() == 0){
                    if (k3 != ""){
                        k2  = k3;
                        k3 = "";
                    }
                }

                if(texts.size() < allEds.size()+3){
                    counter = counter - ((allEds.size()+3)-texts.size());
                }

                for(int i = 3; i < texts.size(); i++){
                    String string = "Kompliment"+(i+1);
                    editor.putString(string, texts.get(i));

                }
                editor.putInt("KomplimentCounter", counter);

                editor.putString("Kompliment1", k1);
                editor.putString("Kompliment2", k2);
                editor.putString("Kompliment3", k3);

                if(!aenderung){
                    startActivity(new Intent(this, Ressource.class));
                }
                else{
                    editor.putBoolean("TabelleÄndern", false);
                    startActivity(new Intent(this, UebersichtTable.class));
                }

                break;

            case R.id.addRowK_Button:
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
