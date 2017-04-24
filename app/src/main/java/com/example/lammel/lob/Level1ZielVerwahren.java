package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level1ZielVerwahren extends AppCompatActivity implements View.OnClickListener {

    Button zielVerwahren_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_zielverwahren);
        this.setTitle("LOB - Dein Ziel");
        zielVerwahren_Button = (Button) findViewById(R.id.zielVerwahren_Button);
        zielVerwahren_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //startActivity level 2
        startActivity(new Intent(this, Level2Veraenderung.class));
    }
}
