package com.example.lammel.lob;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class Level1Onboarding extends FragmentActivity implements View.OnClickListener, AppCompatCallback {



    private int counter = 0;

    //Footer_Fragment Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;
    private Button ziel;
    private ImageButton sungrey;
    private ImageButton sunyellow;
    private ImageButton sun;
    private Button ressource;
    private ImageButton glowgrey;
    private ImageButton glowcolor;
    private ImageButton glow;
    private Button loesung;
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;


    // Button and more
    private Button weiter_button;
    private TextView onboard;
    private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1_onboarding);
        this.setTitle("LOB - Family Treasure");

        //Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level1_onboarding, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.level1_onboarding);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        onboardingProzessStarten();


        //Footer_Fragment Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);
        forward.setVisibility(View.VISIBLE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.GONE);


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

    private void onboardingProzessStarten() {
        weiter_button = (Button) findViewById(R.id.weiter_button);
        onboard = (TextView) findViewById(R.id.onboard_1);
        weiter_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.weiter_button:
            if (counter == 0) {
                onboard.setText("Du bist der Meinung Familie ist wichtig und gibt Energie?\nFinden wir auch!\nLerne hier deine Konflikte zu lösen.");
                counter++;
                break;
            } else if (counter == 1) {
                onboard.setText("Die Ursache des Konflikts ist nicht entscheidend, wichtig ist, wie du die Beziehung in der Zukunft siehst.");
                counter++;
                break;
            } else if (counter == 2) {
                onboard.setText("Deswegen spielt das Problem hier eine untergeordnete Rolle.\nEs geht darum, dass du dich gut fühlst und das erreicht man nicht durch die Problem- sondern Lösungsfokussierung.");
                counter++;
                break;
            } else if (counter == 3) {
                startActivity(new Intent(this, Level1Problemdefinition.class));

                //startActivity(new Intent(this, Staerkeinsel.class));

                //startActivity(new Intent(this, Rueckblick.class));

                //startActivity(new Intent(this, SonneDerErkenntnisStart.class));
            }

            case R.id.back_Button:
                if (counter == 0) {
                    startActivity(new Intent(this, MainActivity.class));
                }
                else if(counter == 3){
                    onboard.setText("Deswegen spielt das Problem hier eine untergeordnete Rolle.\nEs geht darum, dass du dich gut fühlst und das erreicht man nicht durch die Problem- sondern Lösungsfokussierung.");
                    counter--;
                    break;
                } else if (counter == 2) {
                    onboard.setText("Die Ursache des Konflikts ist nicht entscheidend, wichtig ist, wie du die Beziehung in der Zukunft siehst.");
                    counter--;
                    break;
                } else if (counter == 1) {
                    onboard.setText("Du bist der Meinung Familie ist wichtig und gibt Energie?\nFinden wir auch!\nLerne hier deine Konflikte zu lösen.");
                    counter--;
                    break;
                }


            case R.id.forward_Button:
                if (counter == 0) {
                    onboard.setText("Du bist der Meinung Familie ist wichtig und gibt Energie?\nFinden wir auch!\nLerne hier deine Konflikte zu lösen.");
                    counter++;
                    break;
                } else if (counter == 1) {
                    onboard.setText("Die Ursache des Konflikts ist nicht entscheidend, wichtig ist, wie du die Beziehung in der Zukunft siehst.");
                    counter++;
                    break;
                } else if (counter == 2) {
                    onboard.setText("Deswegen spielt das Problem hier eine untergeordnete Rolle.\nEs geht darum, dass du dich gut fühlst und das erreicht man nicht durch die Problem- sondern Lösungsfokussierung.");
                    counter++;
                    break;
                } else if (counter == 3) {
                    startActivity(new Intent(this, Level1Problemdefinition.class));

                    //startActivity(new Intent(this, Staerkeinsel.class));

                    //startActivity(new Intent(this, Rueckblick.class));

                    //startActivity(new Intent(this, SonneDerErkenntnisStart.class));
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
