package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Level4Uebersicht extends AppCompatActivity implements View.OnClickListener{

    private ImageView sun;
    private ImageView eye;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_uebersicht);
        this.setTitle("LOB - Konsolidierung");

        sun = (ImageView) findViewById(R.id.Sonne_imageView);
        sun.setOnClickListener(this);

        eye = (ImageView) findViewById(R.id.Sonne_imageView);
        eye.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Sonne_imageView:
                startActivity(new Intent(this, Level3UebungStart.class));
                break;

            case R.id.Auge_imageView:
                startActivity(new Intent(this, Level3UebungStart.class));
                break;
            default:
                break;
        }

    }
}
