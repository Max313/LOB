package com.example.lammel.lob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by lammel on 11.04.17.
 */

public class Level1Onboarding extends AppCompatActivity implements View.OnClickListener{

    private int counter = 0;

    private Button weiter_button;
    private TextView onboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1_onboarding);
        this.setTitle("LOB - Family Treasure");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        onboardingProzessStarten();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.activity_main){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onboardingProzessStarten() {
        weiter_button = (Button) findViewById(R.id.weiter_button);

        onboard = (TextView) findViewById(R.id.onboard_1);
        weiter_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (counter == 0){
            onboard.setText("Du bist der Meinung Familie ist wichtig und gibt Energie?\nFinden wir auch!\nLerne hier deine Konflikte zu lösen.");
            counter++;
        }
        else if (counter == 1){
            onboard.setText("Die Ursache des Konflikts ist nicht entscheidend, wichtig ist, wie du die Beziehung in der Zukunft siehst.");
            counter++;
        }
        else if (counter == 2){
            onboard.setText("Deswegen spielt das Problem hier eine untergeordnete Rolle.\nEs geht darum, dass du dich gut fühlst und das erreicht man nicht durch die Problem- sondern Lösungsfokussierung.");
            counter++;
        }
        else if (counter == 3){
            //startActivity(new Intent(this, Level1Problemdefinition.class));

            startActivity(new Intent(this, Staerkeinsel.class));

            //startActivity(new Intent(this, Rueckblick.class));

            //startActivity(new Intent(this, SonneDerErkenntnisStart.class));
        }
    }
}
