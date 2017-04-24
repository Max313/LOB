package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sonne3 extends AppCompatActivity implements View.OnClickListener{

    private Button weiter;
    private Button uebersicht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne3);

        this.setTitle("LOB - Sonne der Erkenntnis 3/8");

        weiter = (Button) findViewById(R.id.Weiter3_Button);
        weiter.setOnClickListener(this);

        uebersicht = (Button) findViewById(R.id.zurUebersicht3_Button);
        uebersicht.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Weiter3_Button:
                startActivity(new Intent(this, Sonne4.class));
                break;

            case R.id.zurUebersicht3_Button:
                startActivity(new Intent(this, Level4SonneDerErkenntnis.class));
                break;

            default:
                break;
        }


    }
}
