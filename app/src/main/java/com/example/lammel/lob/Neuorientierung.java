package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Neuorientierung extends AppCompatActivity implements View.OnClickListener{

    private Button zuLevel2;
    private Button zumEnde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuorientierung);

        this.setTitle("LOB - Neuorientierung");

        zuLevel2 = (Button) findViewById(R.id.level2_Button);
        zuLevel2.setOnClickListener(this);

        zumEnde = (Button) findViewById(R.id.wEnde_Button);
        zumEnde.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.level2_Button:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

            case R.id.wEnde_Button:
                startActivity(new Intent(this, Ende.class));
                break;
        }

    }
}
