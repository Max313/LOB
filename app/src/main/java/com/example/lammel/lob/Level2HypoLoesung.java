package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2HypoLoesung extends AppCompatActivity implements View.OnClickListener{

    private Button hypoStarten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_hypo_loesung);

        hypoStarten = (Button) findViewById(R.id.hypoLoesung_Button);
        hypoStarten.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Level2Phantasiereise.class));
    }
}
