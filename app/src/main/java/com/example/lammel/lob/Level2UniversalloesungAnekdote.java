package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2UniversalloesungAnekdote extends AppCompatActivity implements View.OnClickListener{

    private Button universalAnekdote_Weiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_universalloesung_anekdote);
        universalAnekdote_Weiter = (Button) findViewById(R.id.universalAnekdote_ButtonWeiter);
        universalAnekdote_Weiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Level2UniversalloesungWeiter.class));
    }
}
