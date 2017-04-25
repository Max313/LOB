package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mantra extends AppCompatActivity implements View.OnClickListener{

    private Button weiter;
    private int source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantra);
        source = getIntent().getExtras().getInt("Source");
        this.setTitle("LOB - Kompliment");

        weiter = (Button) findViewById(R.id.mantraWeiter_Button);
        weiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(source == 0){
            startActivity(new Intent(this, SonneDerErkenntnisStart.class));
        }

        else if(source == 1){
            startActivity(new Intent(this, Rueckblick.class));

        }

    }
}
