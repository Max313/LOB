package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Level2Phantasiereise extends AppCompatActivity implements View.OnClickListener{

    private Button phantasieWeiter;
    private TextView phantasieText;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_phantasiereise);
        phantasieText = (TextView) findViewById(R.id.phantasie_Textview);
        phantasieWeiter = (Button) findViewById(R.id.phantasie_ButtonWeiter);
        phantasieWeiter.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(counter == 0){
            phantasieText.setText("Was wird dann anders sein?\nWas wirst du oder ihr tun, was du oder ihr lange nicht getan habt?\nWer von deinen Freunden oder Bekannten wird es zuerst bemerken?");
            counter++;
        }
        else if(counter == 1){
            phantasieText.setText("Woran werden sie es bemerken?\nUnd wie werden sie darauf reagieren?\nUnd wie wirst du wiederum deren Reaktion aufnehmen?");
            counter++;
        }
        else if(counter == 2){
            startActivity(new Intent(this, Level2Universalloesung.class));
            counter = 0;
        }
    }
}
