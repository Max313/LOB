package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Level4InselFragen extends AppCompatActivity implements View.OnClickListener{

    private int counter = 0;
    private Button weiter;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_insel_fragen);
        this.setTitle("LOB - Insel des Sehenden - 1/4");

        weiter = (Button) findViewById(R.id.frage1_Button);
        weiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        txt = (TextView) findViewById(R.id.frage1_TextView);

        if(counter == 0){
            this.setTitle("LOB - Insel des Sehenden - 2/4");
            txt.setText("Was ist inzwischen geschehen, von dem du möchtest, dass es weiterhin in dieser Weise geschieht?");
            counter++;
        }

        else if(counter == 1){
            this.setTitle("LOB - Insel des Sehenden - 3/4");

            txt.setText("Was hat sich seither schon an Positiven entwickelt, so dass du jetzt das Gefüh hast, einen Schritt weiter zu sein?");
            counter++;
        }

        else if(counter == 2) {
            this.setTitle("LOB - Insel des Sehenden - 4/4");
            txt.setText("Was von dem, was sich seit der letzten Nutzung der App verändert hat, hältst du für am wichtigsten?");
            counter++;
        }
        else if(counter == 3){
            counter = 0;
            startActivity(new Intent(this, SonneDerErkenntnisStart.class));
        }

    }
}
