package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Kompliment extends AppCompatActivity  implements View.OnClickListener{

    private Button weiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kompliment);
        this.setTitle("LOB - Stärkeinsel - Kompliment");
        weiter = (Button) findViewById(R.id.weiterzuRessource_Button);
        weiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, Ressource.class));
    }
}
