package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class Level2NeuerWeg extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;

    //Buttons and more
    private Button neuerWeg;
    private Button neuesZiel;
    private TextView wegText;

    //Counter der anzeigt zum wie vielten Mal die Activity aufgerufen wurde
    private int wegCounter;
    private AppCompatDelegate delegate;

    //shared Preferences als Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    //Hier kann man sein Ziel ändern oder man bekommt einen neuen Weg angeboten
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_neuer_weg);
        this.setTitle("LOB - Lösungswege");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level2_neuer_weg, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level2_neuer_weg);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setVisibility(View.GONE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.VISIBLE);


        //Buttons Action
        wegCounter = getIntent().getExtras().getInt("WegCounter");
        wegText = (TextView) findViewById(R.id.neuerWeg_Textview);
        switch(wegCounter){
            case 1:
                wegText.setText("Solche Situationen in Worte zu fassen kann eine Herausforderung darstellen. Probiere doch einfach mal einen neuen Weg aus um der Lösung einen Schritt näher zu kommen. Oder hat sich dein Ziel inzwischen verändert?");
                break;

            case 2:
                wegText.setText("Jeder muss für sich selbst den richtigen Weg finden. Das bedeutet nicht dass man bisher auf dem falschen Weg ist sonder nur, dass man noch nicht alle ausprobiert hat. Inzwischen könnte sich aber auch dein Ziel geändert haben.");
                break;

            case 3:
                wegText.setText("Viele Wege führen zum Ziel. Es kann immer sein dass es genau der nächste ist. Sollte sich dein Ziel seit dem letzten Mal verändert haben, dann kannst du es jetzt ändern.");
                break;

            case 4:
                wegText.setText("Es gibt viele Abzweigungen und Kreuzungen. Sofort den richtigen Weg zu nehmen schafft kaum einer, aber auf jeden Fall geht es immer weiter. Oder willst du dein Ziel noch einmal abändern? ");
                break;

            default:
                break;
        }
        neuerWeg = (Button) findViewById(R.id.neuerWeg_ButtonLoesung);
        neuerWeg.setOnClickListener(this);
        neuesZiel = (Button) findViewById(R.id.neuerWeg_ButtonZiel);
        neuesZiel.setOnClickListener(this);
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
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().clear().commit();
                startNew();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startNew(){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.neuerWeg_ButtonLoesung:
                //der nächste Lösungsweg wird gestartet
                if(wegCounter==0){
                    startActivity(new Intent(this, Level2Ausnahmen.class));
                    break;
                }
                else if(wegCounter==1){
                    startActivity(new Intent(this, Level2HypoLoesung.class));
                    break;
                }
                else if(wegCounter==2){
                    startActivity(new Intent(this, Level2Universalloesung.class));
                    break;
                }
                else if(wegCounter==3){
                    startActivity(new Intent(this, Level2Exitstrategie.class));
                    break;
                }
                else if(wegCounter==4){
                    //finaler Screen, keine weiteren Lösungswege -> App wahrscheinlich nicht die richtige Lösung
                    startActivity(new Intent(this, Level2KeineLoesung.class));
                    break;
                }

            case R.id.neuerWeg_ButtonZiel:
                //Ziel wird geändert
                Intent intent = new Intent(v.getContext(), Level2ZieldefinitionNeu.class);
                intent.putExtra("WegCounter", wegCounter);
                startActivity(intent);
                break;

            case R.id.back_Button:
                Intent intent2 = new Intent(v.getContext(), Level2Loesungswege.class);
                intent2.putExtra("LoesungsCounter", wegCounter);
                startActivity(intent2);
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
