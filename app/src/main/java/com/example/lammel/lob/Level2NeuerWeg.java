package com.example.lammel.lob;

import android.content.Intent;
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
    private Button neuerWeg;
    private Button neuesZiel;
    private int wegCounter;
    private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_neuer_weg);
        this.setTitle("LOB - Atolle");

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
        neuerWeg = (Button) findViewById(R.id.neuerWeg_ButtonLoesung);
        neuerWeg.setOnClickListener(this);
        neuesZiel = (Button) findViewById(R.id.neuerWeg_ButtonZiel);
        neuesZiel.setOnClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.tabelle).setEnabled(false);
        menu.findItem(R.id.Sonne).setEnabled(false);
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
                startActivity(new Intent(this, Level1Zieldefinition.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
