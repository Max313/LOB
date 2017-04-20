package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Level4SonneDerErkenntnis extends AppCompatActivity implements View.OnClickListener{

    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;
    private TextView txt6;
    private TextView txt7;
    private TextView txt8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_sonne_der_erkenntnis);
        this.setTitle("LOB - Sonne der Erkenntnis");

        txt1 = (TextView) findViewById(R.id.sonne1);
        txt1.setOnClickListener(this);

        txt2 = (TextView) findViewById(R.id.sonne2);
        txt2.setOnClickListener(this);

        txt3 = (TextView) findViewById(R.id.sonne3);
        txt3.setOnClickListener(this);

        txt4 = (TextView) findViewById(R.id.sonne4);
        txt4.setOnClickListener(this);

        txt5 = (TextView) findViewById(R.id.sonne5);
        txt5.setOnClickListener(this);

        txt6 = (TextView) findViewById(R.id.sonne6);
        txt6.setOnClickListener(this);

        txt7 = (TextView) findViewById(R.id.sonne7);
        txt7.setOnClickListener(this);

        txt8 = (TextView) findViewById(R.id.sonne8);
        txt8.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.sonne1:
                startActivity(new Intent(this, Sonne1.class));
                break;

            case R.id.sonne2:
                startActivity(new Intent(this, Sonne2.class ));
                break;

            case R.id.sonne3:
                startActivity(new Intent(this, Sonne3.class));
                break;

            case R.id.sonne4:
                startActivity(new Intent(this, Sonne4.class));
                break;

            case R.id.sonne5:
                startActivity(new Intent(this, Sonne5.class));
                break;

            case R.id.sonne6:
                startActivity(new Intent(this, Sonne6.class));
                break;

            case R.id.sonne7:
                startActivity(new Intent(this, Sonne7.class));
                break;

            case R.id.sonne8:
                startActivity(new Intent(this, Sonne8.class));
                break;


        }


    }
}
