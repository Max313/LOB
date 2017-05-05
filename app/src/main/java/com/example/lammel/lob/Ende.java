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

import org.w3c.dom.Text;

public class Ende extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;

    //Buttons and more
    private Button weiter;
    private TextView txt;
    private int counter;
    private int source;
    private AppCompatDelegate delegate;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ende);

        this.setTitle("LOB - ...und am Ende...");

        //Set Status - Footer
        saved = getSharedPreferences(PREFS_NAME, 0);

        if(saved.getInt("loesungStatus", 0) < 2){
            editor = saved.edit();
            editor.putInt("loesungStatus", 2);
            editor.apply();
        }


        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.ende, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_ende);

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


        //Button and more (in action)
        weiter = (Button) findViewById(R.id.EndeWeiter_Button);
        weiter.setOnClickListener(this);

        txt = (TextView) findViewById(R.id.Ende_TextView);

        source = getIntent().getExtras().getInt("Source");


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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.EndeWeiter_Button:
                if(counter == 0){
                    this.setTitle("LOB - Geschenke");
                    txt.setText("Wenn du gleich die Schatzruhe erreichst habe ich darin ein persönliches Geschenk für dich. Denn ich möchte mich für die gute Zusammenarbeit, dein Durchhaltevermögen " +
                            "und deine Kreativität bedanken. Zudem möchte ich dir vorschlagen, dass du dich auch selbst beschenkst. Vielleicht findest du eine Kleinigkeit die für dich deine Kompetenzen " +
                            "symbolisiert. Nimm dir ruhig Zeit bei der Wahl, zelebriere es. Vielleicht ist es ein Bild oder eine Figur, du wirst es sicher erkennen wenn du es siehst.");
                    counter++;
                    break;
                }

                else if(counter == 1) {
                    this.setTitle("LOB - Verabschiedung");
                    txt.setText("Ganz zum Schluss gibt es noch etwas Wichtiges zu sagen: Alles, wirklich alles, was du an Veränderungen erreicht hast, ist ganz allein aus deiner Idee und aus deinen " +
                            "Kräften erwachsen. LOB hat dir nur geholfen, dass du diese Ideen ausformulierst und du deine Fähigkeiten dann auch nutzt. Mach genau so weiter! Denk daran, die Lösung steckt immer schon in DIR!");
                    weiter.setVisibility(View.GONE);
                    counter = 0;
                    break;

                }

            case R.id.back_Button:
                if(counter == 0){
                    if(source == 0){
                        startActivity(new Intent(this, Rueckblick.class));
                    }
                    else{
                    startActivity(new Intent(this, Neuorientierung.class));
                    break;
                    }
                }
                else if(counter == 1){
                    startActivity(new Intent(this, Ende.class));
                    break;
                }

                else if(counter == 2){
                    this.setTitle("LOB - Geschenke");
                    txt.setText("Wenn du gleich die Schatzruhe erreichst habe ich darin ein persönliches Geschenk für dich. Denn ich möchte mich für die gute Zusammenarbeit, dein Durchhaltevermögen " +
                            "und deine Kreativität bedanken. Zudem möchte ich dir vorschlagen, dass du dich auch selbst beschenkst. Vielleicht findest du eine Kleinigkeit die für dich deine Kompetenzen " +
                            "symbolisiert. Nimm dir ruhig Zeit bei der Wahl, zelebriere es. Vielleicht ist es ein Bild oder eine Figur, du wirst es sicher erkennen wenn du es siehst.");
                    counter--;
                    break;

                }

            case R.id.forward_Button:
                    if(counter == 0){
                        this.setTitle("LOB - Geschenke");
                        txt.setText("Wenn du gleich die Schatzruhe erreichst habe ich darin ein persönliches Geschenk für dich. Denn ich möchte mich für die gute Zusammenarbeit, dein Durchhaltevermögen " +
                                "und deine Kreativität bedanken. Zudem möchte ich dir vorschlagen, dass du dich auch selbst beschenkst. Vielleicht findest du eine Kleinigkeit die für dich deine Kompetenzen " +
                                "symbolisiert. Nimm dir ruhig Zeit bei der Wahl, zelebriere es. Vielleicht ist es ein Bild oder eine Figur, du wirst es sicher erkennen wenn du es siehst.");
                        counter++;
                        break;
                    }

                    else if(counter == 1) {
                        this.setTitle("LOB - Verabschiedung");
                        txt.setText("Ganz zum Schluss gibt es noch etwas Wichtiges zu sagen: Alles, wirklich alles, was du an Veränderungen erreicht hast, ist ganz allein aus deiner Idee und aus deinen " +
                                "Kräften erwachsen. LOB hat dir nur geholfen, dass du diese Ideen ausformulierst und du deine Fähigkeiten dann auch nutzt. Mach genau so weiter! Denk daran, die Lösung steckt immer schon in DIR!");
                        weiter.setVisibility(View.GONE);
                        forward.setVisibility(View.GONE);
                        counter = 0;
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
