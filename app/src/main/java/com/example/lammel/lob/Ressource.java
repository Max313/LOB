package com.example.lammel.lob;

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
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

public class Ressource extends FragmentActivity implements View.OnClickListener, AppCompatCallback {


    //Buttons and more
    private Button weiter;
    private Button q;
    private TextView ressource;
    private AppCompatDelegate delegate;
    private Boolean aenderung;

    //Tabelleninhalt
    private String r1, r2, r3;
    private EditText txt1, txt2, txt3;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ressource);

        this.setTitle("Stärkeinsel");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.ressource, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_ressource);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level3));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.baum);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Buttons and more in action
        weiter = (Button) findViewById(R.id.weiterzuUebersicht_Button);
        weiter.setOnClickListener(this);
        weiter.setEnabled(false);

        //q = (Button) findViewById(R.id.erklaerungR_Button);
        //q.setOnClickListener(this);

        ressource = (TextView) findViewById(R.id.ressourcenTextView);
        ressource.setOnClickListener(this);

        //Tabelle befüllen falls nötig
        saved = getSharedPreferences(PREFS_NAME, 0);
        r1 = saved.getString("Ressource1", "");
        r2 = saved.getString("Ressource2", "");
        r3 = saved.getString("Ressource3", "");

        txt1 = (EditText) findViewById(R.id.ressource1EditText);
        txt2 = (EditText) findViewById(R.id.ressource2EditText);
        txt3 = (EditText) findViewById(R.id.ressource3EditText);

        if(r1 != "" || r2 != "" || r3 != ""){

            txt1.setText(r1);
            txt2.setText(r2);
            txt3.setText(r3);

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
                r1 = txt1.getText().toString().trim();

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
                r2 = txt2.getText().toString().trim();

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
                r3 = txt3.getText().toString().trim();

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

            case R.id.action_delete:
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().clear().commit();
                deleteFiles();
                startNew();
                return true;

            case R.id.action_help:
                AlertDialog.Builder builder = new AlertDialog.Builder(Ressource.this);
                builder.setTitle("Ressource");
                builder.setMessage("Ressourcen die du zur Lösung einer schwierigen Situation beitragen. \nz.B. Umsicht");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR = builder.create();
                dialogR.show();

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
        AlertDialog.Builder builder = new AlertDialog.Builder(Ressource.this);
        saved = getSharedPreferences(PREFS_NAME, 0);
        aenderung = saved.getBoolean("TabelleÄndern", false);
        editor = saved.edit();
        switch (view.getId()) {

            case R.id.ressourcenTextView:

                builder.setTitle("Ressource");
                builder.setMessage("Ressourcen die du zur Lösung einer schwierigen Situation beitragen. \nz.B. Umsicht");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                dialogV.show();
                break;

            case R.id.weiterzuUebersicht_Button:

                if(r1.length() == 0){
                    if (r2.length() == 0) {
                        r1 = r3;
                        r3 = "";
                    }
                    else{
                        if(r3.length() != 0){
                            r1 = r2;
                            r2 = r3;
                            r3 = "";
                        }
                        else{
                            r1 = r2;
                            r2 = "";
                        }
                    }
                }
                else if(r2.length() == 0){
                    if (r3 != ""){
                        r2  = r3;
                        r3 = "";
                    }
                }

                editor.putString("Ressource1", r1);
                editor.putString("Ressource2", r2);
                editor.putString("Ressource3", r3);

                startActivity(new Intent(this, UebersichtTable.class));
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
