package com.example.lammel.lob;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2NeuerWeg extends AppCompatActivity implements View.OnClickListener {

    private Button neuerWeg;
    private Button neuesZiel;
    private int wegCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_neuer_weg);
        this.setTitle("LOB - Atolle");
        wegCounter = getIntent().getExtras().getInt("WegCounter");
        neuerWeg = (Button) findViewById(R.id.neuerWeg_ButtonLoesung);
        neuerWeg.setOnClickListener(this);
        neuesZiel = (Button) findViewById(R.id.neuerWeg_ButtonZiel);
        neuesZiel.setOnClickListener(this);
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
                //startActivity(new Intent(this, .class));
                break;

            default:
                break;
    }

}

}
