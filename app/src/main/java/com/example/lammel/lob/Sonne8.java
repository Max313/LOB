package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sonne8 extends AppCompatActivity implements View.OnClickListener{

    private Button weiter;
    private Button uebersicht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne8);

        this.setTitle("LOB - Sonne der Erkenntnis 8/8");

        weiter = (Button) findViewById(R.id.Weiter8_Button);
        weiter.setOnClickListener(this);

        uebersicht = (Button) findViewById(R.id.zurUebersicht8_Button);
        uebersicht.setOnClickListener(this);

     }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.Weiter8_Button:
                Intent intent = new Intent(view.getContext(), Mantra.class);
                intent.putExtra("Source", 1);
                startActivity(intent);
                break;

            case R.id.zurUebersicht8_Button:
                startActivity(new Intent(this, Level4SonneDerErkenntnis.class));
                break;

            default:
                break;
        }

    }
}
