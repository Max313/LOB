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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

public class Staerkeinsel extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Footer Buttons
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
    private TextView verhalten;
    private TextView kompliment;
    private TextView ressourcen;
    private Button speichern;
    private TableLayout table;
    private int backCounter;
    private AppCompatDelegate delegate;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staerkeinsel);
        this.setTitle("LOB - Stärkeinsel - Intro");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.staerkeinsel, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_staerkeinsel);

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
        verhalten = (TextView) findViewById(R.id.verhaltenTextView);
        verhalten.setOnClickListener(this);

        kompliment = (TextView) findViewById(R.id.komplimentTextView);
        kompliment.setOnClickListener(this);

        ressourcen = (TextView) findViewById(R.id.ressourcenTextView);
        ressourcen.setOnClickListener(this);

        speichern = (Button) findViewById(R.id.weiterStaerkeButton);
        speichern.setOnClickListener(this);

        table = (TableLayout) findViewById(R.id.tableSmall);
        table.setOnClickListener(this);

        backCounter = getIntent().getExtras().getInt("LoesungsCounter");

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

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Staerkeinsel.this);
        switch (view.getId()) {
            case R.id.verhaltenTextView:

                builder.setTitle("Verhalten");
                builder.setMessage("Welches Verhalten an dir fällt dir positiv auf und wie würdest du es charakterisieren. \nz.B. Selbst in einer schwierigen Situation versuche ich das beste für mich und die betroffenen Personen zu machen.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                dialogV.show();
                break;

            case R.id.komplimentTextView:
                builder.setTitle("Kompliment");
                builder.setMessage("Gebe dir selbst ein Kompliment, so wie du es auch einem guten Freund geben würdest. \nz.B. Ich gehe umsichtig und überlegt an die Situation heran.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK = builder.create();
                dialogK.show();
                break;
            case R.id.ressourcenTextView:
                builder.setTitle("Ressourcen");
                builder.setMessage("Ressourcen die du zur Lösung einer schwierigen Situation beitragen. \nz.B. Umsicht");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR = builder.create();
                dialogR.show();
                break;

            case R.id.weiterStaerkeButton:
                startActivity(new Intent(this, Verhalten.class));
                break;


            case R.id.tableSmall:
                startActivity(new Intent(this, Verhalten.class));
                break;

            case R.id.back_Button:
                Intent intent = new Intent(view.getContext(), Wunderbar.class);
                intent.putExtra("LoesungsCounter", backCounter);
                startActivity(intent);
                break;

            case R.id.forward_Button:
                startActivity(new Intent(this, Verhalten.class));
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
