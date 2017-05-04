package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class Ressource extends FragmentActivity implements View.OnClickListener, AppCompatCallback {


    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;

    //Buttons and more
    public static String FIRSTMESSAGE_RESSOURCE;
    public static String SECONDMESSAGE_RESSOURCE;
    public static String THIRDMESSAGE_RESSOURCE;

    private Button weiter;
    private TextView ressource;
    private AppCompatDelegate delegate;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ressource);

        this.setTitle("LOB - Stärkeinsel - Ressourcen");

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
        delegate.setSupportActionBar(toolbar);

        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);
        forward.setVisibility(View.VISIBLE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.GONE);


        //Buttons and more in action
        weiter = (Button) findViewById(R.id.weiterzuUebersicht_Button);
        weiter.setOnClickListener(this);

        ressource = (TextView) findViewById(R.id.ressourcenTextView);
        ressource.setOnClickListener(this);

        final EditText txt = (EditText) findViewById(R.id.ressource1EditText);
        txt.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt.length() == 0) {
                    weiter.setEnabled(false); //disable send button if no text entered
                    forward.setEnabled(false);
                }
                else {
                    weiter.setEnabled(true);  //otherwise enable
                    forward.setEnabled(true);
                }

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
        AlertDialog.Builder builder = new AlertDialog.Builder(Ressource.this);
        switch (view.getId()) {
            case R.id.ressourcenTextView:

                builder.setTitle("Verhalten");
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

                EditText edit1Text = (EditText) findViewById(R.id.ressource1EditText);
                FIRSTMESSAGE_RESSOURCE = edit1Text.getText().toString();

                EditText edit2Text = (EditText) findViewById(R.id.ressource2EditText);
                SECONDMESSAGE_RESSOURCE = edit2Text.getText().toString();

                EditText edit3Text = (EditText) findViewById(R.id.ressource3EditText);
                THIRDMESSAGE_RESSOURCE = edit3Text.getText().toString();

                startActivity(new Intent(this, UebersichtTable.class));
                break;

            case R.id.back_Button:
                startActivity(new Intent(this, Kompliment.class));
                break;

            case R.id.forward_Button:
                EditText edit4Text = (EditText) findViewById(R.id.ressource1EditText);
                FIRSTMESSAGE_RESSOURCE = edit4Text.getText().toString();

                EditText edit5Text = (EditText) findViewById(R.id.ressource2EditText);
                SECONDMESSAGE_RESSOURCE = edit5Text.getText().toString();

                EditText edit6Text = (EditText) findViewById(R.id.ressource3EditText);
                THIRDMESSAGE_RESSOURCE = edit6Text.getText().toString();

                startActivity(new Intent(this, UebersichtTable.class));
                break;


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
