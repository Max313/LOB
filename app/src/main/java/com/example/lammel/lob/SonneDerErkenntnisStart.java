package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SonneDerErkenntnisStart extends AppCompatActivity implements View.OnClickListener{

    private Button weiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne_der_erkenntnis_start);

        this.setTitle("LOB - Sonne der Erkenntnis");


        weiter = (Button) findViewById(R.id.zurSonne_Button);
        weiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        startActivity(new Intent(this, Level4SonneDerErkenntnis.class));

    }
}
