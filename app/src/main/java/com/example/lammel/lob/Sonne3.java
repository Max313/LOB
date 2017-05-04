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

public class Sonne3 extends FragmentActivity implements View.OnClickListener, AppCompatCallback {


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
    private Button ressource;
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Buttons and more
    private Button weiter;
    private Button uebersicht;
    private Boolean tour;
    private Intent intent;
    private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne3);

        this.setTitle("LOB - Sonne der Erkenntnis 3/8");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.sonne3_xml, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_sonne3);

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


        //Buttons and more on action
        tour = getIntent().getExtras().getBoolean("Tour");

        weiter = (Button) findViewById(R.id.Weiter3_Button);
        weiter.setOnClickListener(this);

        uebersicht = (Button) findViewById(R.id.zurUebersicht3_Button);
        uebersicht.setOnClickListener(this);

        if(tour){
            uebersicht.setVisibility(View.GONE);
        }

        else{
            weiter.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
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

            case R.id.tabelle:
                startActivity(new Intent(this, UebersichtTable.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Weiter3_Button:
                intent = new Intent(view.getContext(), Sonne4.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            case R.id.zurUebersicht3_Button:
                intent = new Intent(view.getContext(), Level4SonneDerErkenntnis.class);
                intent.putExtra("Source", 3);
                startActivity(intent);
                break;

            case R.id.back_Button:
                if(tour){
                    intent = new Intent(view.getContext(), Sonne2.class);
                    intent.putExtra("Tour", true);
                    startActivity(intent);
                    break;
                }
                else{
                    intent = new Intent(view.getContext(), Level4SonneDerErkenntnis.class);
                    intent.putExtra("Source", 3);
                    startActivity(intent);
                    break;
                }

            case R.id.forward_Button:
                if(tour){
                    intent = new Intent(view.getContext(), Sonne4.class);
                    intent.putExtra("Tour", true);
                    startActivity(intent);
                    break;
                }
                else{
                    intent = new Intent(view.getContext(), Level4SonneDerErkenntnis.class);
                    intent.putExtra("Source", 3);
                    startActivity(intent);
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
