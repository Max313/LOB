package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
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

public class UebersichtTable extends FragmentActivity implements View.OnClickListener, AppCompatCallback {


    //Footer_Fragment Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;
    private ImageButton sungrey;
    private ImageButton sunyellow;
    private ImageButton sun;
    private ImageButton glowgrey;
    private ImageButton glowcolor;
    private ImageButton glow;
    private Button ziel;
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Buttons and more
    public static Boolean aenderung = false;
    private Button weiter;
    private Button aendern1;
    private Button aendern2;
    private Button aendern3;
    private Button aendern4;
    private AppCompatDelegate delegate;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht_table);
        this.setTitle("LOB - Stärkeinsel - Übersicht");

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
        delegate.setSupportActionBar(toolbar);


        //Footer_Fragment Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);
        forward.setVisibility(View.VISIBLE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.GONE);


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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setTableContent() {

        TextView text1View = (TextView) findViewById(R.id.Uebersicht1_1);
        text1View.setOnClickListener(this);

        if (Verhalten.FIRSTMESSAGE_VERHALTEN.length() <= 12) {
            text1View.setText(Verhalten.FIRSTMESSAGE_VERHALTEN);
        } else {
            String shortString = Verhalten.FIRSTMESSAGE_VERHALTEN.substring(0, 9) + "...";
            text1View.setText(shortString);
        }


        TextView text2View = (TextView) findViewById(R.id.Uebersicht2_1);
        text2View.setOnClickListener(this);

        if (Verhalten.SECONDMESSAGE_VERHALTEN.length() <= 12) {
            text2View.setText(Verhalten.SECONDMESSAGE_VERHALTEN);
        } else {
            String shortString = Verhalten.SECONDMESSAGE_VERHALTEN.substring(0, 9) + "...";
            text2View.setText(shortString);
        }


        TextView text3View = (TextView) findViewById(R.id.Uebersicht3_1);
        text3View.setOnClickListener(this);

        if (Verhalten.THIRDMESSAGE_VERHALTEN.length() <= 12) {
            text3View.setText(Verhalten.THIRDMESSAGE_VERHALTEN);
        } else {
            String shortString = Verhalten.THIRDMESSAGE_VERHALTEN.substring(0, 9) + "...";
            text3View.setText(shortString);
        }


        TextView text4View = (TextView) findViewById(R.id.Uebersicht1_2);
        text4View.setOnClickListener(this);

        if (Kompliment.FIRSTMESSAGE_KOMPLIMENT.length() <= 12) {
            text4View.setText(Kompliment.FIRSTMESSAGE_KOMPLIMENT);
        } else {
            String shortString = Kompliment.FIRSTMESSAGE_KOMPLIMENT.substring(0, 9) + "...";
            text4View.setText(shortString);
        }


        TextView text5View = (TextView) findViewById(R.id.Uebersicht2_2);
        text5View.setOnClickListener(this);

        if (Kompliment.SECONDMESSAGE_KOMPLIMENT.length() <= 12) {
            text5View.setText(Kompliment.SECONDMESSAGE_KOMPLIMENT);
        } else {
            String shortString = Kompliment.SECONDMESSAGE_KOMPLIMENT.substring(0, 9) + "...";
            text5View.setText(shortString);
        }


        TextView text6View = (TextView) findViewById(R.id.Uebersicht3_2);
        text6View.setOnClickListener(this);

        if (Kompliment.THIRDMESSAGE_KOMPLIMENT.length() <= 12) {
            text6View.setText(Kompliment.THIRDMESSAGE_KOMPLIMENT);
        } else {
            String shortString = Kompliment.THIRDMESSAGE_KOMPLIMENT.substring(0, 9) + "...";
            text6View.setText(shortString);
        }


        TextView text7View = (TextView) findViewById(R.id.Uebersicht1_3);
        text7View.setOnClickListener(this);

        if (Ressource.FIRSTMESSAGE_RESSOURCE.length() <= 12) {
            text7View.setText(Ressource.FIRSTMESSAGE_RESSOURCE);
        } else {
            String shortString = Ressource.FIRSTMESSAGE_RESSOURCE.substring(0, 9) + "...";
            text7View.setText(shortString);
        }

        TextView text8View = (TextView) findViewById(R.id.Uebersicht2_3);
        text8View.setOnClickListener(this);

        if (Ressource.SECONDMESSAGE_RESSOURCE.length() <= 12) {
            text8View.setText(Ressource.SECONDMESSAGE_RESSOURCE);
        } else {
            String shortString = Ressource.SECONDMESSAGE_RESSOURCE.substring(0, 9) + "...";
            text8View.setText(shortString);
        }


        TextView text9View = (TextView) findViewById(R.id.Uebersicht3_3);
        text9View.setOnClickListener(this);

        if (Ressource.THIRDMESSAGE_RESSOURCE.length() <= 12) {
            text9View.setText(Ressource.THIRDMESSAGE_RESSOURCE);
        } else {
            String shortString = Ressource.THIRDMESSAGE_RESSOURCE.substring(0, 9) + "...";
            text8View.setText(shortString);
        }

    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UebersichtTable.this);
        switch (view.getId()) {
            case R.id.Uebersicht1_1:

                builder.setTitle("Verhalten");
                builder.setMessage(Verhalten.FIRSTMESSAGE_VERHALTEN);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV1 = builder.create();
                dialogV1.show();
                break;

            case R.id.Uebersicht1_2:

                builder.setTitle("Verhalten");
                builder.setMessage(Verhalten.SECONDMESSAGE_VERHALTEN);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV2 = builder.create();
                dialogV2.show();
                break;

            case R.id.Uebersicht1_3:

                builder.setTitle("Verhalten");
                builder.setMessage(Verhalten.THIRDMESSAGE_VERHALTEN);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV3 = builder.create();
                dialogV3.show();
                break;

            case R.id.Uebersicht2_1:

                builder.setTitle("Kompliment");
                builder.setMessage(Kompliment.FIRSTMESSAGE_KOMPLIMENT);
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
                builder.setMessage(Kompliment.SECONDMESSAGE_KOMPLIMENT);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK2 = builder.create();
                dialogK2.show();
                break;

            case R.id.Uebersicht2_3:

                builder.setTitle("Kompliment");
                builder.setMessage(Kompliment.THIRDMESSAGE_KOMPLIMENT);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK3 = builder.create();
                dialogK3.show();
                break;

            case R.id.Uebersicht3_1:

                builder.setTitle("Ressourcen");
                builder.setMessage(Ressource.FIRSTMESSAGE_RESSOURCE);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR1 = builder.create();
                dialogR1.show();
                break;

            case R.id.Uebersicht3_2:

                builder.setTitle("Ressourcen");
                builder.setMessage(Ressource.SECONDMESSAGE_RESSOURCE);
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
                builder.setMessage(Ressource.THIRDMESSAGE_RESSOURCE);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR3 = builder.create();
                dialogR3.show();
                break;

            case R.id.weiterStaerkeButton:
                //startActivity(new Intent(this, Level3UebungStart.class));
                startActivity(new Intent(this, Level4InselDesSehenden.class));

                break;

            case R.id.aendern1_Button:
                aenderung = true;
                startActivity(new Intent(this, Verhalten.class));
                break;

            case R.id.aendern2_Button:
                aenderung = true;
                startActivity(new Intent(this, Kompliment.class));
                break;

            case R.id.aendern3_Button:
                startActivity(new Intent(this, Ressource.class));
                break;

            case R.id.aendern4_Button:
                startActivity(new Intent(this, Verhalten.class));
                break;

            case R.id.back_Button:
                startActivity(new Intent(this, Ressource.class));
                break;

            case R.id.forward_Button:
                //startActivity(new Intent(this, Level3UebungStart.class));
                startActivity(new Intent(this, Level4InselDesSehenden.class));
                break;

            default:
                break;
        }
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
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
