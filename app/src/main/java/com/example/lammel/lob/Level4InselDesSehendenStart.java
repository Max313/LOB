package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level4InselDesSehendenStart extends AppCompatActivity implements View.OnClickListener{

    private Button fragen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_insel_des_sehenden_start);
        this.setTitle("LOB - Insel des Sehenden");
        fragen = (Button) findViewById(R.id.fragenStart_Button);
        fragen.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, Level4InselFrage1.class));

    }
}
