package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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

import java.io.File;

public class UebersichtTable extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Buttons and more
    private Button weiter;
    private Button aendern1;
    private Button aendern2;
    private Button aendern3;
    private Button aendern4;
    private AppCompatDelegate delegate;


    //Tabelleninhalt
    private String v1, v2, v3, k1, k2, k3, r1, r2, r3;


    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht_table);
        this.setTitle("Stärkeinsel - Übersicht");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.uebersicht_table, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_uebersicht_table);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level3));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.baum);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Buttons and more in action
        weiter = (Button) findViewById(R.id.weiterStaerkeButton);
        weiter.setOnClickListener(this);


        aendern1 = (Button) findViewById(R.id.aendern1_Button);
        aendern1.setOnClickListener(this);

        aendern2 = (Button ) findViewById(R.id.aendern2_Button);
        aendern2.setOnClickListener(this);

        aendern3 = (Button) findViewById(R.id.aendern3_Button);
        aendern3.setOnClickListener(this);

        aendern4 = (Button) findViewById(R.id.aendern4_Button);
        aendern4.setOnClickListener(this);
        this.setTableContent();


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

    private void setTableContent() {

        saved = getSharedPreferences(PREFS_NAME, 0);
        v1 = saved.getString("Verhalten1", "");
        v2 = saved.getString("Verhalten2", "");
        v3 = saved.getString("Verhalten3", "");
        k1 = saved.getString("Kompliment1", "");
        k2 = saved.getString("Kompliment2", "");
        k3 = saved.getString("Kompliment3", "");
        r1 = saved.getString("Ressource1", "");
        r2 = saved.getString("Ressource2", "");
        r3 = saved.getString("Ressource3", "");

        //Verhalten aus Zeile 1 in Tabelle eintragen
        TextView text1View = (TextView) findViewById(R.id.Uebersicht1_1);
        text1View.setOnClickListener(this);

        if (v1.length() <= 12) {
            text1View.setText(v1);
        } else {
            String shortString = v1.substring(0, 9) + "...";
            text1View.setText(shortString);
        }

        //Verhalten aus Zeile 2 in Tabelle eintragen
        TextView text2View = (TextView) findViewById(R.id.Uebersicht2_1);
        text2View.setOnClickListener(this);

            if (v2.length() <= 12) {
                text2View.setText(v2);
            } else {
                String shortString = v2.substring(0, 9) + "...";
                text2View.setText(shortString);
            }


        //Verhalten aus Zeile 3 in Tabelle eintragen
        TextView text3View = (TextView) findViewById(R.id.Uebersicht3_1);
        text3View.setOnClickListener(this);

            if (v3.length() <= 12) {
                text3View.setText(v3);
            } else {
                String shortString = v3.substring(0, 9) + "...";
                text3View.setText(shortString);
            }


        //Kompliment aus Zeile 1 in Tabelle eintragen
        TextView text4View = (TextView) findViewById(R.id.Uebersicht1_2);
        text4View.setOnClickListener(this);

        if (k1.length() <= 12) {
            text4View.setText(k1);
        } else {
            String shortString = k1.substring(0, 9) + "...";
            text4View.setText(shortString);
        }


        //Kompliment aus Zeile 2 in Tabelle eintragen
        TextView text5View = (TextView) findViewById(R.id.Uebersicht2_2);
        text5View.setOnClickListener(this);

            if (k2.length() <= 12) {
                text5View.setText(k2);
            } else {
                String shortString = k2.substring(0, 9) + "...";
                text5View.setText(shortString);
            }

        //Kompliment aus Zeile 3 in Tabelle eintragen
        TextView text6View = (TextView) findViewById(R.id.Uebersicht3_2);
        text6View.setOnClickListener(this);

            if (k3.length() <= 12) {
                text6View.setText(k3);
            } else {
                String shortString = k3.substring(0, 9) + "...";
                text6View.setText(shortString);
            }


        //Ressource aus Zeile 1 in Tabelle eintragen
        TextView text7View = (TextView) findViewById(R.id.Uebersicht1_3);
        text7View.setOnClickListener(this);

        if (r1.length() <= 12) {
            text7View.setText(r1);
        } else {
            String shortString = r1.substring(0, 9) + "...";
            text7View.setText(shortString);
        }

        //Ressource aus Zeile 2 in Tabelle eintragen
        TextView text8View = (TextView) findViewById(R.id.Uebersicht2_3);
        text8View.setOnClickListener(this);


            if (r2.length() <= 12) {
                text8View.setText(r2);
            } else {
                String shortString = r2.substring(0, 9) + "...";
                text8View.setText(shortString);
            }


        //Ressource aus Zeile 3 in Tabelle eintragen
        TextView text9View = (TextView) findViewById(R.id.Uebersicht3_3);
        text9View.setOnClickListener(this);


            if (r3.length() <= 12) {
                text9View.setText(r3);
            } else {
                String shortString = r3.substring(0, 9) + "...";
                text9View.setText(shortString);
            }
        }


    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UebersichtTable.this);
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        switch (view.getId()) {


            case R.id.Uebersicht1_1:

                builder.setTitle("Verhalten");
                builder.setMessage(v1);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV1 = builder.create();
                dialogV1.show();
                break;

            case R.id.Uebersicht2_1:

                builder.setTitle("Verhalten");
                builder.setMessage(v2);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV2 = builder.create();
                dialogV2.show();
                break;

            case R.id.Uebersicht3_1:

                builder.setTitle("Verhalten");
                builder.setMessage(v3);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV3 = builder.create();
                dialogV3.show();
                break;

            case R.id.Uebersicht1_2:

                builder.setTitle("Kompliment");
                builder.setMessage(k1);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK1 = builder.create();
                dialogK1.show();
                break;

            case R.id.Uebersicht2_2:

                builder.setTitle("Kompliment");
                builder.setMessage(k2);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK2 = builder.create();
                dialogK2.show();
                break;

            case R.id.Uebersicht3_2:

                builder.setTitle("Kompliment");
                builder.setMessage(k3);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK3 = builder.create();
                dialogK3.show();
                break;

            case R.id.Uebersicht1_3:

                builder.setTitle("Ressourcen");
                builder.setMessage(r1);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR1 = builder.create();
                dialogR1.show();
                break;

            case R.id.Uebersicht2_3:

                builder.setTitle("Ressourcen");
                builder.setMessage(r2);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR2 = builder.create();
                dialogR2.show();
                break;

            case R.id.Uebersicht3_3:

                builder.setTitle("Ressourcen");
                builder.setMessage(r3);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR3 = builder.create();
                dialogR3.show();
                break;

            case R.id.weiterStaerkeButton:

                if (!saved.getBoolean("pause1", false)){
                startActivity(new Intent(this, Pause.class));
                }

                else{
                    //Set Status - Footer
                    saved = getSharedPreferences(PREFS_NAME, 0);
                    editor = saved.edit();

                    if(saved.getInt("ressourceStatus", 0) < 2){
                        editor.putInt("ressourceStatus", 2);
                    }

                    else if(saved.getInt("sonneStatus", 0) < 1){
                        editor.putInt("sonneStatus", 1);
                    }
                    editor.putInt("tabStatus", 4);
                    editor.apply();

                    startActivity(new Intent(this, Level4Start.class));
                }

                break;

            case R.id.aendern1_Button:
                editor.putBoolean("TabelleÄndern", true);
                startActivity(new Intent(this, Verhalten.class));
                break;

            case R.id.aendern2_Button:
                editor.putBoolean("TabelleÄndern", true);
                startActivity(new Intent(this, Kompliment.class));
                break;

            case R.id.aendern3_Button:
                startActivity(new Intent(this, Ressource.class));
                break;

            case R.id.aendern4_Button:
                startActivity(new Intent(this, Verhalten.class));
                break;

            default:
                break;
        }

        editor.putBoolean("MenuTabelle", true);
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
