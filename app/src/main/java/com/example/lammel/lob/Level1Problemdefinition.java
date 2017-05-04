package com.example.lammel.lob;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by lammel on 11.04.17.
 */
public class Level1Problemdefinition extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;
    private Button ziel;
    private Button ressource;
    private Button loesung;
    private ImageButton sungrey;
    private ImageButton sunyellow;
    private ImageButton sun;
    private ImageButton glowgrey;
    private ImageButton glowcolor;
    private ImageButton glow;
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Button and more
    private Button problem;
    private Button keinProblem;
    private AppCompatDelegate delegate;
    private int status;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1_problemdefinition);
        this.setTitle("LOB - Das Problem");

        status = 1;

        if(status > MainActivity.zielStatus){
            MainActivity.zielStatus = status;
        }

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level1_problemdefinition, fragment);
        transaction.commit();

        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.level1_problemdefinition);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        //Footer_Fragment Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setVisibility(View.GONE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.VISIBLE);

        /**Ziel Button
        ziel = (Button) findViewById(R.id.ziel_Button);

        //Idee Button
        glowgrey = (ImageButton) findViewById(R.id.gluehbirneDurchsichtig_Button);
        glowcolor = (ImageButton) findViewById(R.id.gluehbirneDunkel_Button);
        glow = (ImageButton) findViewById(R.id.gluehbirneLeuchtend_Button);

        //Ressource Button
        ressource = (Button) findViewById(R.id.ressourcen_Button);

        //Sun Button
        sungrey = (ImageButton) findViewById(R.id.sonneGrau_Button);
        sunyellow = (ImageButton) findViewById(R.id.sonneLeer_Button);
        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);

        //Loesung Button
        loesung = (Button) findViewById(R.id.loesung_Button);

        //Tabs
        eins = (TextView) findViewById(R.id.footer1_TextView);
        zwei = (TextView) findViewById(R.id.footer2_TextView);
        drei = (TextView) findViewById(R.id.footer3_TextView);
        vier = (TextView) findViewById(R.id.footer4_TextView);
        fuenf = (TextView) findViewById(R.id.footer5_TextView);*/

        //Buttons
        problem = (Button) findViewById(R.id.problemButton);
        keinProblem = (Button) findViewById(R.id.keinProblemButton);
        problem.setOnClickListener(this);
        keinProblem.setOnClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.ziel).setEnabled(false);
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.keinProblemButton:
                startActivity(new Intent(this, Level1Zieldefinition.class));
                break;

            case R.id.problemButton:
                startActivity(new Intent(this, Level1_ProblemBeschreibung.class));
                break;

            case R.id.back_Button:
                startActivity(new Intent(this, Level1Onboarding.class));
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
