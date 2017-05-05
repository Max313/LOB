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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class Level2Loesungswege extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

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

    //Viewteile der Activity (Buttons, Textviews,..)
    private Button fertig;
    private Button mirFaelltNichtsEin;
    private TextView anfangsText;
    private int loesungsCounter;
    private AppCompatDelegate delegate;
    private Boolean txt1leer, txt2leer, txt3leer;
    private EditText txt1, txt2, txt3;


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
        this.setTitle("LOB - Lösungswege");


        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level2_loesungswege, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level2_loesungswege);

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


        //Action and more
        loesungsCounter = getIntent().getExtras().getInt("LoesungsCounter");
        anfangsText = (TextView) findViewById(R.id.textView4);
        switch(loesungsCounter){
            case 1:
                anfangsText.setText("Es ist nicht einfach solch eine Ausnahme zu finden. Vielleicht hat dir diese Übung dabei geholfen auf einen Lösungsweg zu kommen.");
                break;

            case 2:
                anfangsText.setText("Das Leben kann anders sein als es momentan scheint. Hast du eine Idee bekommen, auf welchem Weg du deine Zukunft verbessern kannst?");
                break;

            case 3:
                anfangsText.setText("Oft kann eine kleine Veränderung des Verhaltens große Wirkung zeigen. Hast du eine Möglichkeit gefunden um eine positive Veränderung zu erzielen?");
                break;

            case 4:
                anfangsText.setText("Auch wenn sich dein Problem übermächtig anfühlt gibt es bestimmt Sachen, die in deinem Leben positiv laufen. Woraus schöpfst du Energie und Freude?");
                break;

            case 5:
                anfangsText.setText("Klicke einfach auf den Weiter-Button wenn du die App weiter erforschen willst und vielleicht tun sich zu einem späteren Zeitpunkt noch Lösungswege auf.");
                break;

            default:
                break;
        }

        //Buttons
        mirFaelltNichtsEin = (Button) findViewById(R.id.loesungswege_ButtonNichts);
        mirFaelltNichtsEin.setOnClickListener(this);
        mirFaelltNichtsEin.setEnabled(false);
        fertig = (Button) findViewById(R.id.loesungswege_ButtonFertig);

        if(loesungsCounter != 5){
            fertig.setEnabled(false);
            mirFaelltNichtsEin.setEnabled(true);
        }

        fertig.setOnClickListener(this);

        saved = getSharedPreferences(PREFS_NAME, 0);
        weg1 = saved.getString("loesungsweg1", "");
        weg2 = saved.getString("loesungsweg2", "");
        weg3 = saved.getString("loesungsweg3", "");


        txt1 = (EditText) findViewById(R.id.loesungswege_edittext1);
        if(weg1 != ""){
            txt1.setText(weg1);
            fertig.setEnabled(true);
        }

        txt2 = (EditText) findViewById(R.id.loesungswege_edittext2);
        if(weg2 != ""){
            txt2.setText(weg2);
            fertig.setEnabled(true);
        }

        txt3 = (EditText) findViewById(R.id.loesungswege_edittext3);
        if(weg3 != ""){
            txt3.setText(weg3);
            fertig.setEnabled(true);
        }
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
                startActivity(new Intent(this, MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void enableButton(){

        txt1.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt1.length() == 0)
                fertig.setEnabled(false); //disable button if no text entered
                else
                fertig.setEnabled(true);  //otherwise enable
                weg1 = txt1.getText().toString();

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
                else
                    fertig.setEnabled(true);  //otherwise enable
                weg2 = txt2.getText().toString();

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
                if(txt3.length() == 0)
                    fertig.setEnabled(false); //disable button if no text entered
                else
                    fertig.setEnabled(true);  //otherwise enable
                    weg3 = txt3.getText().toString();

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }

    @Override
    public void onClick(View v) {

        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        editor.putString("loesungsweg1", weg1);
        editor.putString("loesungsweg2", weg2);
        editor.putString("loesungsweg3", weg3);
        editor.apply();

        switch (v.getId()) {
            case R.id.loesungswege_ButtonFertig:
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

            case R.id.loesungswege_ButtonNichts:
                if(loesungsCounter == 0){
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 0);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 1){
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 1);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 2){
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 2);
                    startActivity(intent);
                    break;
                }
               else if(loesungsCounter == 3) {
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 3);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 4) {
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 4);
                    startActivity(intent);
                    break;
                }

            case R.id.back_Button:
                if(loesungsCounter == 0){
                    startActivity(new Intent(this, Level2VeraenderungJa.class));
                    break;
                }

                else if(loesungsCounter == 1) {
                    startActivity(new Intent(this, Level2Ausnahmen.class));
                    break;
                }

                else if(loesungsCounter == 2){
                    startActivity(new Intent(this, Level2Phantasiereise.class));
                    break;
                }

                else if(loesungsCounter == 3){
                    startActivity(new Intent(this, Level2UniversalloesungWeiter.class));
                    break;
                }

                else if(loesungsCounter == 4){
                    startActivity(new Intent(this, Level2Exitstrategie.class));
                    break;
                }

                else if(loesungsCounter == 5){
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
