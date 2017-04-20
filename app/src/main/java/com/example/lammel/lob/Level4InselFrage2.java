package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level4InselFrage2 extends AppCompatActivity implements View.OnClickListener{

    private Button weiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_insel_frage2);
        this.setTitle("LOB - Insel des Sehenden - 2/4");

        weiter = (Button) findViewById(R.id.frage2_Button);
        weiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        startActivity(new Intent(this, Level4InselFrage3.class));

    }
}
