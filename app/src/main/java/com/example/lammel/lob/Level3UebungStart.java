package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level3UebungStart extends AppCompatActivity implements View.OnClickListener{

    private Button uebung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3_uebung_start);
        this.setTitle("LOB - Stärkeninseln");
        uebung = (Button) findViewById(R.id.button3);
        uebung.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, Level4InselDesSehenden.class));

    }
}
